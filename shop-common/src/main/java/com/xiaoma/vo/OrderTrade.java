package com.xiaoma.vo;

import com.xiaoma.pojo.MemberReceiveAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTrade {
    /*收货人地址*/
    private List<MemberReceiveAddress> receiveAddressesList;

    /*订单展示的购物项（购物车中选中的购物项）*/
    private List<CartItem> orderItems;

    /*存储每个购物项的库存状态（skuId=true/false代表有无库存）*/
    Map<Long,Boolean> skuIdStockStatus;

    /*总金额*/
    private BigDecimal totalPrice;

    /*应付金额*/
    private BigDecimal payPrice;

    /*防重token字段，防止用户反复提交订单*/
    private String orderToken;

    public Integer getTotalNumber(){
        if (!CollectionUtils.isEmpty(orderItems)) {
            return orderItems.stream().map(CartItem::getNumber).reduce(Integer::sum).get();
        }else{
            return 0;
        }
    }

    public BigDecimal getTotalPrice(){
        if (!CollectionUtils.isEmpty(orderItems)) {
            return orderItems.stream().
                    map(cartItem -> cartItem.getPrice().multiply(new BigDecimal(cartItem.getNumber() + "")))
                    .reduce(BigDecimal::add).get();
        }else{
            return new BigDecimal("0");
        }
    }

    /*不考虑折扣、抵价、运费等情况*/
    public BigDecimal getPayPrice(){
        return getTotalPrice();
    }
}
