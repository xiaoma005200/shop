package com.xiaoma;

import com.xiaoma.feign.ProductClient;
import com.xiaoma.feign.SKUClient;
import com.xiaoma.pojo.SearchSkuInfo;
import com.xiaoma.pojo.SkuInfo;
import com.xiaoma.repository.SearchSkuInfoRepository;
import com.xiaoma.service.SKUService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ImportDataToESTest {

    @Autowired
    SearchSkuInfoRepository searchSkuInfoRepository;

    @Autowired
    ProductClient productClient;

    @Test
    public void importData(){
        // 1.查询出486三级分类下的所有skuInfo(实际上应该导入所有skuInfo)
        List<SkuInfo> skuInfoList = productClient.findAll(486L);

        ArrayList<SearchSkuInfo> searchSkuInfoList = new ArrayList<>();
        // 2.遍历skuInfoList将每个skuInfo的属性拷贝到对应的searchSkuInfo属性值中(mysql中使用的是skuInfo对象,ES中使用的SearchSkuInfo对象)
        skuInfoList.forEach(skuInfo -> {
            SearchSkuInfo searchSkuInfo = new SearchSkuInfo();
            BeanUtils.copyProperties(skuInfo,searchSkuInfo);// 对象拷贝
            searchSkuInfoList.add(searchSkuInfo);
        });

        // 3.将searchSkuInfoList保存到ES中
        searchSkuInfoList.forEach(searchSkuInfo -> searchSkuInfoRepository.save(searchSkuInfo));
        searchSkuInfoList.forEach(searchSkuInfoRepository::save);

    }
}
