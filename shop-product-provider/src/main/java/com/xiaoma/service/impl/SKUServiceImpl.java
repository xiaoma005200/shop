package com.xiaoma.service.impl;

import com.google.gson.Gson;
import com.xiaoma.exception.LockStockException;
import com.xiaoma.mapper.custom.SkuInfoCustomMapper;
import com.xiaoma.mapper.custom.WareSkuCustomMapper;
import com.xiaoma.mapper.generate.SkuAttrValueMapper;
import com.xiaoma.mapper.generate.SkuImageMapper;
import com.xiaoma.mapper.generate.SkuInfoMapper;
import com.xiaoma.mapper.generate.SkuSaleAttrValueMapper;
import com.xiaoma.pojo.*;
import com.xiaoma.service.SKUService;
import com.xiaoma.util.RedisUtils;
import com.xiaoma.vo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class SKUServiceImpl implements SKUService {

    @Autowired
    SkuInfoMapper skuInfoMapper;

    @Autowired
    SkuImageMapper skuImageMapper;

    @Autowired
    SkuAttrValueMapper skuAttrValueMapper;

    @Autowired
    SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Autowired
    SkuInfoCustomMapper skuInfoCustomMapper;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    WareSkuCustomMapper wareSkuCustomMapper;

    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        //1.保存sku基本信息=>sku_info
        skuInfoMapper.insertSelective(skuInfo);

        //2.保存sku对应的图片信息=>sku_image
        skuInfo.getSkuImageList().forEach(skuImage -> {
            skuImage.setSkuId(skuInfo.getId());
            skuImageMapper.insertSelective(skuImage);
        });

        //3.保存sku对应的基本属性(基本属性值)=>sku_attr_value
        skuInfo.getSkuAttrValueList().forEach(skuAttrValue -> {
            skuAttrValue.setSkuId(skuInfo.getId());
            skuAttrValueMapper.insertSelective(skuAttrValue);
        });

        //4.保存sku对应的销售属性(销售属性值)=>sku_sale_attr_value
        skuInfo.getSkuSaleAttrValueList().forEach(skuSaleAttrValue -> {
            skuSaleAttrValue.setSkuId(skuInfo.getId());
            skuSaleAttrValueMapper.insertSelective(skuSaleAttrValue);
        });
    }

    @Override
    public SkuInfo findBySkuInfoId(Integer skuInfoId) {
        /**
         * 首先去redis中根据skuInfoId查询是否缓存了skuInfo相关信息
         *      如果没有,说明是第一次访问这个sku,那么就查询数据库,再把相关数据存入redis
         *      如果有,说明之前缓存过这个sku，那么就从redis中取数据,不在查询数据库
         * redis中key命名规范
         *       对象名:对象对应的id:对象属性 例如:product:11:stock  id为11的商品的库存量
         */

        String key = "sku:" + skuInfoId + ":info";
        Object value = redisUtils.get(key);
        SkuInfo skuInfo;

        if(value !=null){ // 说明redis缓存中又有数据,直接从缓存中取数据
            skuInfo = new Gson().fromJson(value.toString(), SkuInfo.class);
        }else{ // 说明缓存中没有数据,需要查询数据库并缓存到redis
            //使用redis对共享数据加锁
            //每个线程绑定对应的锁(每个线程对应唯一个UUID)
            String currentThreadLock = UUID.randomUUID().toString();
            boolean isLock = redisUtils.setNX("sku:" + skuInfoId + ":lock", currentThreadLock, 5, TimeUnit.SECONDS);

            if(isLock){ //isLock为true,说明当前线程持有锁成功 ==> 持有锁成功可以去访问数据库
                // 1.查询Sku的基本信息==>sku_info表
                skuInfo = skuInfoMapper.selectByPrimaryKey(skuInfoId.longValue());

                if(skuInfo!=null){
                    // 2.查询sku对应的图片列表==>sku_image表
                    SkuImageExample skuImageExample = new SkuImageExample();
                    skuImageExample.createCriteria().andSkuIdEqualTo(skuInfoId.longValue());
                    List<SkuImage> skuImages = skuImageMapper.selectByExample(skuImageExample);

                    // 3.查询sku对应的销售信息==>sku_sale_attr_value
                    SkuSaleAttrValueExample skuSaleAttrValueExample = new SkuSaleAttrValueExample();
                    skuSaleAttrValueExample.createCriteria().andSkuIdEqualTo(skuInfoId.longValue());
                    List<SkuSaleAttrValue> skuSaleAttrValueList = skuSaleAttrValueMapper.selectByExample(skuSaleAttrValueExample);

                    // 4.将skuImages设置到skuInfo中
                    skuInfo.setSkuImageList(skuImages);

                    // 5.将skuSaleAttrValueList设置到skuInfo中
                    skuInfo.setSkuSaleAttrValueList(skuSaleAttrValueList);

                    // 6.将查询的数据skuInfo缓存到redis
                    //redisUtils.set(key,new Gson().toJson(skuInfo));

                    // 方案一: 随机1min-5min,"0<=timeout<5" ==> "1<=timeout<6"(以分钟为单位,随机数范围小)
                    //redisUtils.set(key,new Gson().toJson(skuInfo), ThreadLocalRandom.current().nextInt(5)+1,TimeUnit.MINUTES);

                    // 方案二: 随机60s-301s,"60<=timeout<301" ==> "0<=timeout<241"
                    redisUtils.set(key,new Gson().toJson(skuInfo), ThreadLocalRandom.current().nextInt(241)+60,TimeUnit.SECONDS);
                }else{
                    // 将"null"数据进行缓存，并且设置他的过期时间为5min
                    redisUtils.set(key,new Gson().toJson(null),5, TimeUnit.MINUTES);
                }
                /**
                 * 有可能操作数据库的时间 < 锁的过期时间,会导致操作完数据库锁还没有释放
                 * 此时我们就使用del主动删除锁,让其它线程可以立即获得锁,而不是一直等待锁过期
                 */
                /*Object LockValue = redisUtils.get("sku:" + skuInfoId + ":lock");
                if(LockValue!=null && LockValue.equals(currentThreadLock)){ //if条件为真,说明上述查询数据库完成之后锁仍然没有释放,此时主动释放锁
                    redisUtils.del("sku:" + skuInfoId + ":lock");
                }*/
                //利用lua脚本将判断锁和删除锁合成一个原子操作(避免出现if判断锁是否存在后直接切换到其他线程执行,锁在自动删除后再切换回来执行del操作发现已无锁可删的问题)
                redisUtils.execLuaScript("if redis.call('get',KEYS[1])== ARGV[1] then return redis.call('del',KEYS[1]) else " +
                        "return 0 end", Long.class, Collections.singletonList("sku:" + skuInfoId + ":lock"), currentThreadLock);
            }else{ //isLock为false,说明当前线程持有锁失败 ==> 持有锁失败递归调用findBySkuInfoId方法继续请求
                try {
                    Thread.sleep(1000); //当前线程睡眠1s给其它线程访问锁的机会
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return findBySkuInfoId(skuInfoId);
            }
        }
        return skuInfo;
    }

    @Override
    public Map<String, Long> findSkuSaleAttrValuesBySpuId(Long spuId) {
        // 1.查询出skuInfo和销售属性值信息
        List<SkuInfo> skuInfoList = skuInfoCustomMapper.selectSkuSaleAttrValuesBySpuId(spuId);

        //2.构造map映射表
        /**
         * sale_attr_value_id(key)     skuId(value)
         * 328#331                     165
         * 329#330                     167
         * 328#330                     164
         * 329#331                     168
         * 329#332                     169
         * 328#332                     166
         */
        Map<String, Long> map = skuInfoList.stream().collect(Collectors.toMap(skuInfo ->
                skuInfo.getSkuSaleAttrValueList()
                        .stream()
                        .map(skuSaleAttrValue ->
                                skuSaleAttrValue.getSaleAttrValueId() + "")
                        .collect(Collectors.joining("#")),SkuInfo::getId));
        return map;
    }

    @Override
    public List<SkuInfo> findAll(Long catalog3Id) {
        // 1.根据三级分类id查询所有的skuInfo
        SkuInfoExample skuInfoExample = new SkuInfoExample();
        skuInfoExample.createCriteria().andCatalog3IdEqualTo(catalog3Id);
        List<SkuInfo> skuInfoList = skuInfoMapper.selectByExample(skuInfoExample);

        // 2.根据每个sku的id查询出每个sku对应的属性id和属性值id
        skuInfoList.forEach(skuInfo -> {
            SkuAttrValueExample skuAttrValueExample = new SkuAttrValueExample();
            skuAttrValueExample.createCriteria().andSkuIdEqualTo(skuInfo.getId());
            List<SkuAttrValue> skuAttrValueList = skuAttrValueMapper.selectByExample(skuAttrValueExample);
            skuInfo.setSkuAttrValueList(skuAttrValueList);
        });
        return skuInfoList;
    }

    @Override
    public String selectSkuIdByValueIds(String saleAttrValueIds) {
        return skuInfoCustomMapper.selectBySaleAttrValueIds(saleAttrValueIds);
    }

    @Override
    public BigDecimal findPriceBySkuId(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectByPrimaryKey(skuId);
        return new BigDecimal(skuInfo.getPrice()+"");
    }

    @Override
    public Map<Long, Boolean> getSkuStock(List<Long> skuIds) {
        return skuIds.stream().collect(Collectors.toMap(skuId -> skuId, skuId -> {
            Long stockCount = wareSkuCustomMapper.getSkuStock(skuId);
            return stockCount == null ? false : stockCount > 0;
        }));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void orderLockStock(List<OrderItem> orderItemList) throws LockStockException {
        /********保存锁定任务信息:锁定哪个订单*********/
        /**
         * 每个购物项都必须库存锁定,只有每个购物项都锁定成功,才算整个订单锁定成功,只要有一个购物项锁定失败,整体失败
         * 每个购物项锁定库存的算法:
         *   1.根据购物项的skuId获取其对应所有的仓库的库存信息
         *   2.针对sku对应的每个仓库依次锁定
         *     一旦有一个仓库锁定成功,这个sku就算锁定成功,退出
         *     只有该sku对应的所有仓库全部锁定失败,才算这个sku锁定库存失败
         *
         *  128购买5件
         *    128,1号仓库,10,6
         *    128,6号仓库,20,12
         *    128,5号仓库,30,15
         *
         *  129购买7件
         *      129,2号仓库,20,17
         *      129,3号仓库,30,26
         *      129,7号仓库,40,38
         */
        for (OrderItem orderItem : orderItemList) {
            //1.根据订单项(购物项)中的skuId获取其所有的库存信息
            WareSkuExample wareSkuExample = new WareSkuExample();
            wareSkuExample.createCriteria().andSkuIdEqualTo(orderItem.getSkuId());
            List<WareSku> wareSkuList = wareSkuCustomMapper.selectByExample(wareSkuExample);

            //2.实现每个购物项锁定库存的算法
            Optional<WareSku> firstWareSku = wareSkuList.stream()
                    .filter(wareSku -> wareSku.getStockLocked() + orderItem.getNumber() <= wareSku.getStock())
                    .findFirst();//获取流中的第一个元素,相当于取出第一个可锁定库存的仓库

            if (firstWareSku.isPresent()) {//说明skuId对应的仓库有可以锁定成功的
                //锁定该仓库的库存
                WareSku wareSku = firstWareSku.get();
                wareSku.setStockLocked(wareSku.getStockLocked() + orderItem.getNumber());
                wareSkuCustomMapper.updateByPrimaryKeySelective(wareSku);
            } else {//说明流中无元素,所有的仓库均锁定失败
                throw new LockStockException("skuId:" + orderItem.getSkuId() + "锁定失败");
            }
        }

    }
}
