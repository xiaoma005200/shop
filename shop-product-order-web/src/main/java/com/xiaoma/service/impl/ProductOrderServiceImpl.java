package com.xiaoma.service.impl;

import com.google.gson.Gson;
import com.xiaoma.constant.OrderStatusEnum;
import com.xiaoma.exception.LockStockException;
import com.xiaoma.feign.MemberClient;
import com.xiaoma.feign.ProductClient;
import com.xiaoma.feign.ShopCartClient;
import com.xiaoma.mapper.generate.OrderDetailMapper;
import com.xiaoma.mapper.generate.OrderMapper;
import com.xiaoma.pojo.*;
import com.xiaoma.service.ProductOrderService;
import com.xiaoma.util.IdWorker;
import com.xiaoma.util.RedisUtils;
import com.xiaoma.util.ResultUtils;
import com.xiaoma.vo.*;
import com.xiaoma.vo.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    MemberClient memberClient;

    @Autowired
    ShopCartClient shopCartClient;

    @Autowired
    ProductClient productClient;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Override
    public OrderTrade getOrderTradeData(UserInfo userInfo) {
        OrderTrade orderTrade = new OrderTrade();
        // 1.获取该用户的收货地址
        List<MemberReceiveAddress> receiveAddressList = memberClient.getReceiveAddressByMemberId(Long.parseLong(userInfo.getUserId()));
        orderTrade.setReceiveAddressesList(receiveAddressList);

        // 2.获取购物车中已选中的购物项
        List<CartItem> checkedCartItems = shopCartClient.getCheckedCartItems(userInfo);
        orderTrade.setOrderItems(checkedCartItems);

        // 3.获取购物车中所有购物项的库存状态
        Map<Long, Boolean> skuIdStockStatus = productClient.getSkuStock(checkedCartItems.stream().map(CartItem::getSkuId).collect(Collectors.toList()));
        orderTrade.setSkuIdStockStatus(skuIdStockStatus);

        // 4.放入一个orderToken,防止用户反复创建订单（只创建一份）
        String orderToken = UUID.randomUUID().toString().replaceAll("-", "");
        redisUtils.set("order:token:" + userInfo.getUserId(), orderToken, 30, TimeUnit.MINUTES);
        orderTrade.setOrderToken(orderToken);

        return orderTrade;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class) // 所有异常都要回滚
    public ResponseData<Order> submitOrder(OrderVO orderVO, UserInfo userInfo) throws LockStockException {
        //1.检验用户是否是首次提交订单
        String orderToken = orderVO.getOrderToken();
        /**
         * 如果采用get获取,equals判断,redis删除,这是三步操作,可能引发多线程安全问题
         * 我们考虑把三步操作合成一步,变成整体(原子操作)
         */
        //Object redisToken = redisUtils.get("order:token:" + userInfo.getUserId());
        //if (!StringUtils.isEmpty(orderToken) && orderToken.equals(redisToken)) {
        //    //首次提交
        //    //从redis中删除token
        //} else {
        //    //说明多次提交
        //}
        Long result = (Long) redisUtils.execLuaScript("if redis.call('get',KEYS[1])== ARGV[1] then return redis.call('del',KEYS[1]) else " +
                "return 0 end", Long.class, Collections.singletonList("order:token:" + userInfo.getUserId()), orderToken);
        if (result == 0L) {
            //多次提交
            return ResultUtils.result(OrderStatusEnum.INVALID, null);
        } else {
            //首次提交
            Order order = createOrder(orderVO, userInfo);
            //保存订单,订单详情信息到数据库
            saveOrder(order);
            //针对每个购物项都要锁定库存
            //将一个个orderDetail转换成orderItem
            List<OrderItem> orderItemList = order.getOrderDetailList().stream()
                    .map(orderDetail -> new OrderItem(orderDetail.getProductSkuId(),orderDetail.getProductQuantity(), orderDetail.getOrderSn(),orderDetail.getOrderId()))
                    .collect(Collectors.toList());

            ResponseData<Order> orderLockResp = productClient.orderLockStock(orderItemList);

            if (orderLockResp.getCode().equals(OrderStatusEnum.LOCKED_STOCK_SUCCESS.getCode())) {
                // 下单成功以及库存锁定成功,此时发送消息,测试环境延迟15s被消费
                return ResultUtils.result(OrderStatusEnum.LOCKED_STOCK_SUCCESS, order);
            } else {//锁定失败
                throw new LockStockException(orderLockResp.getMsg());
            }
        }
    }

    @Override
    public PayVO getPayInfoByOrderSn(String orderSn) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andOrderSnEqualTo(orderSn);
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        Order order = CollectionUtils.isEmpty(orderList) ? null : orderList.get(0);

        PayVO payVO = new PayVO();
        payVO.setOrderSn(orderSn);
        payVO.setPayAmount(order.getPayAmount());
        payVO.setSubject("微服务商城订单");
        payVO.setOrderStatus(order.getStatus()); // 设置订单状态
        return payVO;
    }

    @Override
    public List<Order> getOrderListByUser(UserInfo userInfo) {
        // 1.根据member_id查询用户关联的订单
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andMemberIdEqualTo(Long.parseLong(userInfo.getUserId()));
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        orderList.sort(Comparator.comparing(Order::getCreateTime).reversed());// 按照下单顺序降序排列
        // 2.根据orderSn或者order_id查询订单关联的订单明细
        orderList.forEach(order -> {
            OrderDetailExample orderDetailExample = new OrderDetailExample();
            orderDetailExample.createCriteria().andOrderSnEqualTo(order.getOrderSn());
            List<OrderDetail> orderDetailList = orderDetailMapper.selectByExample(orderDetailExample);
            // 查询每个订单明细关联的SkuInfo信息
            orderDetailList.forEach(orderDetail -> {
                orderDetail.setSkuInfo(productClient.findBySkuInfoId(orderDetail.getProductSkuId().intValue()));
            });
            order.setOrderDetailList(orderDetailList);
        });
        return orderList;
    }

    @Override
    public void updateOrderStatus(String orderSn, Integer code) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andOrderSnEqualTo(orderSn);
        Order order = new Order();
        order.setStatus(code);
        orderMapper.updateByExampleSelective(order,orderExample);
    }

    /**
     * 保存订单,订单详情信息到数据库
     * @param order
     */
    private void saveOrder(Order order) {
        // 向订单表中插入信息
        orderMapper.insertSelective(order);
        // 向订单详情表中插入信息
        order.getOrderDetailList().forEach(orderDetail -> {
            orderDetail.setOrderId(order.getId());
            orderDetailMapper.insertSelective(orderDetail);
        });
    }

    /**
     * 向订单表以及订单详情表插入相关信息
     *
     * @param orderVO
     * @param userInfo
     * @return
     */
    private Order createOrder(OrderVO orderVO, UserInfo userInfo) {
        Order order = new Order();
        // 1.生成唯一订单编号orderSn,设置订单创建时间,订单的状态
        String orderSn = new IdWorker().nextId() + "";
        order.setOrderSn(orderSn);
        order.setCreateTime(new Date());
        order.setStatus(OrderStatusEnum.CREATE_NEW.getCode());

        // 2.关联下单用户(当前登录用户)相关的信息
        order.setMemberId(Long.parseLong(userInfo.getUserId()));
        order.setMemberUsername(userInfo.getUsername());

        // 3.关联收货人相关的信息
        MemberReceiveAddress receiveAddress = memberClient.getReceiveAddressByAddrId(orderVO.getAddressId());
        order.setReceiverName(receiveAddress.getName());
        order.setReceiverPhone(receiveAddress.getPhoneNumber());
        order.setReceiverPostCode(receiveAddress.getPostCode());
        order.setReceiverProvince(receiveAddress.getProvince());
        order.setReceiverCity(receiveAddress.getCity());
        order.setReceiverRegion(receiveAddress.getRegion());
        order.setReceiverDetailAddress(receiveAddress.getDetailAddress());

        // 4.从购物车获取最新的选中的购物项,生成相关的订单详情信息
        List<CartItem> orderItems = shopCartClient.getCheckedCartItems(userInfo);
        List<OrderDetail> orderDetailList = orderItems.stream()
                .map(orderItem -> createOrderDetail(orderSn, orderItem))
                .collect(Collectors.toList());
        order.setOrderDetailList(orderDetailList);

        // 5.设置订单的总金额和应付金额
        BigDecimal totalPrice = orderDetailList.stream()
                .map(orderDetail -> orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity() + "")))
                .reduce(BigDecimal::add).get();
        order.setTotalAmount(totalPrice);
        order.setPayAmount(totalPrice);//这里不再考虑优惠,运费相关的

        // 6.设置其它相关字段
        order.setFreightAmount(new BigDecimal(0)); // 默认运费
        order.setPromotionAmount(new BigDecimal(0)); // 默认促销优化金额
        order.setIntegrationAmount(new BigDecimal(0)); // 默认积分抵扣金额
        order.setCouponAmount(new BigDecimal(0)); // 默认优惠券抵扣金额
        order.setDiscountAmount(new BigDecimal(0)); // 默认手动调整金额
        order.setPayType(0); // 创建订单时默认未支付
        order.setSourceType(0); // 默认PC订单
        order.setOrderType(0); // 默认普通订单
        order.setDeliveryCompany("微服务商城物流公司");
        order.setDeliverySn(UUID.randomUUID().toString().replace("-","")); // 随机物流单号
        order.setAutoConfirmDay(15); // 默认自动确认时间
        order.setIntegration(0); // 默认获得积分
        order.setGrowth(0); // 默认成长值
        order.setPromotionInfo("单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠"); // 默认活动信息
        order.setBillType(0); // 默认不开发票
        order.setConfirmStatus(0); // 默认未确认收货

        return order;
    }

    /**
     * @param orderSn   订单的唯一序列号
     * @param orderItem 购物车选中的购物项
     * @return
     */
    private OrderDetail createOrderDetail(String orderSn, CartItem orderItem) {
        OrderDetail orderDetail = new OrderDetail();
        // 1.关联订单相关的信息
        orderDetail.setOrderSn(orderSn);

        // 2.关联相对应的SPU信息
        ProductInfo productInfo = productClient.findSPUBySkuId(orderItem.getSkuId());
        orderDetail.setProductId(productInfo.getId());
        orderDetail.setProductName(productInfo.getProductName());
        if (!(productInfo.getBrand()==null)) {
            orderDetail.setProductBrand(productInfo.getBrand().getName());
        }
        orderDetail.setProductCategoryId(productInfo.getCatalog3Id());

        // 3.关联对应的SKU信息
        orderDetail.setProductSkuId(orderItem.getSkuId());
        orderDetail.setProductQuantity(orderItem.getNumber());
        orderDetail.setProductPrice(orderItem.getPrice());
        orderDetail.setProductAttr(new Gson().toJson(orderItem.getSkuSaleAttrValueList()));

        // 4.设置其它相关字段
        orderDetail.setProductSn(UUID.randomUUID().toString().replace("-",""));
        orderDetail.setProductSkuCode(orderSn);
        orderDetail.setPromotionName("单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠");

        return orderDetail;
    }
}
