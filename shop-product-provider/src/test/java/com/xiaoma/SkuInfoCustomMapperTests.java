package com.xiaoma;

import com.xiaoma.mapper.custom.SkuInfoCustomMapper;
import com.xiaoma.pojo.SkuInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
public class SkuInfoCustomMapperTests {

    @Autowired
    SkuInfoCustomMapper skuInfoCustomMapper;

    @Test
    public void test01(){
        skuInfoCustomMapper.selectSkuSaleAttrValuesBySpuId(102L).forEach(System.out::println);

    }

    @Test
    public void test02(){
        List<SkuInfo> skuInfoList = skuInfoCustomMapper.selectSkuSaleAttrValuesBySpuId(102L);

        Map<String, Long> map = skuInfoList.stream().collect(Collectors.toMap(skuInfo -> skuInfo.getSkuSaleAttrValueList()
                .stream()
                .map(skuSaleAttrValue -> skuSaleAttrValue.getSaleAttrValueId() + "")
                .collect(Collectors.joining("#")), SkuInfo::getId));

        System.out.println(map);
    }
}
