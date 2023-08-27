package com.xiaoma.service.impl;

import com.google.gson.Gson;
import com.xiaoma.mapper.custom.SkuInfoCustomMapper;
import com.xiaoma.mapper.generate.SkuAttrValueMapper;
import com.xiaoma.mapper.generate.SkuImageMapper;
import com.xiaoma.mapper.generate.SkuInfoMapper;
import com.xiaoma.mapper.generate.SkuSaleAttrValueMapper;
import com.xiaoma.pojo.*;
import com.xiaoma.service.SKUService;
import com.xiaoma.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
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
                Object LockValue = redisUtils.get("sku:" + skuInfoId + ":lock");
                if(LockValue!=null && LockValue.equals(currentThreadLock)){ //if条件为真,说明上述查询数据库完成之后锁仍然没有释放,此时主动释放锁
                    redisUtils.del("sku:" + skuInfoId + ":lock");
                }
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

//    @Override
//    @Cacheable(value = "skuInfo",key = "#skuInfoId + ':info'",cacheManager = "cacheManagerTTL")
//    public SkuInfo findBySkuInfoId(Integer skuInfoId) {
//
//        // 1.查询Sku的基本信息==>sku_info表
//        SkuInfo skuInfo = skuInfoMapper.selectByPrimaryKey(skuInfoId.longValue());
//
//        if (skuInfo!=null) {
//            // 2.查询sku对应的图片列表==>sku_image表
//            SkuImageExample skuImageExample = new SkuImageExample();
//            skuImageExample.createCriteria().andSkuIdEqualTo(skuInfoId.longValue());
//            List<SkuImage> skuImages = skuImageMapper.selectByExample(skuImageExample);
//
//            // 3.查询sku对应的销售信息==>sku_sale_attr_value
//            SkuSaleAttrValueExample skuSaleAttrValueExample = new SkuSaleAttrValueExample();
//            skuSaleAttrValueExample.createCriteria().andSkuIdEqualTo(skuInfoId.longValue());
//            List<SkuSaleAttrValue> skuSaleAttrValueList = skuSaleAttrValueMapper.selectByExample(skuSaleAttrValueExample);
//
//            // 4.将skuImages设置到skuInfo中
//            skuInfo.setSkuImageList(skuImages);
//
//            // 5.将skuSaleAttrValueList设置到skuInfo中
//            skuInfo.setSkuSaleAttrValueList(skuSaleAttrValueList);
//
//        }
//        return skuInfo;
//    }


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
}
