package com.xiaoma.service.impl;

import com.xiaoma.pojo.SearchSkuInfo;
import com.xiaoma.service.SearchSkuInfoService;
import com.xiaoma.vo.PageResult;
import com.xiaoma.vo.ShopSearchQuery;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class SearchSkuInfoServiceImplTest {
    @Autowired
    SearchSkuInfoService searchSkuInfoService;

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    void searchByParams() {
        ShopSearchQuery shopSearchQuery = new ShopSearchQuery();
        //shopSearchQuery.setKeyword("七匹狼短袖T恤");
        shopSearchQuery.setKeyword("鸿星尔克");
        shopSearchQuery.setCatalog3Id(486L);

        searchSkuInfoService.searchByParams(shopSearchQuery)
                .getPageData()
                .forEach(System.out::println);

    }

    @Test
    public void testHighlightField() {

        ShopSearchQuery shopSearchQuery = new ShopSearchQuery();
        //shopSearchQuery.setKeyword("七匹狼短袖T恤");
        shopSearchQuery.setKeyword("鸿星尔克");
        //shopSearchQuery.setCatalog3Id(486L);


        //1.通过NativeSearchQueryBuilder和BoolQueryBuilder(布尔查询)构造整个搜索条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //**************************使用BoolQueryBuilder构造布尔查询条件************************
        //三级分类Id参数
        if (shopSearchQuery.getCatalog3Id()!=null) {//不为空才构建查询条件
            boolQueryBuilder.filter(QueryBuilders.termQuery("catalog3Id", shopSearchQuery.getCatalog3Id()));
        }

        //keyword不为空
        if (StringUtils.isNotBlank(shopSearchQuery.getKeyword())) {
            boolQueryBuilder.filter(QueryBuilders.queryStringQuery(shopSearchQuery.getKeyword()).field("skuName"));
        }
        queryBuilder.withQuery(boolQueryBuilder);


        //构建排序字段
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));


        //指定高亮字段和样式
        queryBuilder.withHighlightFields(new HighlightBuilder.Field("skuName")
                .preTags("<font style='color:red'>")
                .postTags("</font>"));


        //2.执行查询
        SearchHits<SearchSkuInfo> skuInfoSearchHits = elasticsearchRestTemplate.search(queryBuilder.build(), SearchSkuInfo.class);


        skuInfoSearchHits.forEach(skuInfoSearchHit -> System.out.println(skuInfoSearchHit.getHighlightFields()));


        //将SearchSkuInfo对象中的skuName属性值替换成高亮的skuName值
        skuInfoSearchHits.stream().filter(skuInfoSearchHit -> !CollectionUtils.isEmpty(skuInfoSearchHit.getHighlightFields()))
                .forEach(skuInfoSearchHit -> skuInfoSearchHit
                        .getContent().setSkuName(skuInfoSearchHit.getHighlightField("skuName")
                                .stream().collect(Collectors.joining())));


        //3.将SearchHits<SearchSkuInfo>转换成List<SearchSkuInfo>
        List<SearchSkuInfo> searchSkuInfoList = skuInfoSearchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

        System.out.println("-----------------------------------");

        skuInfoSearchHits.forEach(skuInfoSearchHit -> System.out.println(skuInfoSearchHit.getContent()));

        //4.将searchSkuInfoList封装到PageResult中
        PageResult<SearchSkuInfo> pageResult = new PageResult<>();
        pageResult.setPageData(searchSkuInfoList);

    }

    @Test
    public void testAggs() {
        ShopSearchQuery shopSearchQuery = new ShopSearchQuery();


        //1.通过NativeSearchQueryBuilder和BoolQueryBuilder(布尔查询)构造整个搜索条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //**************************使用BoolQueryBuilder构造布尔查询条件************************
        //三级分类Id参数
        if (shopSearchQuery.getCatalog3Id()!=null) {//不为空才构建查询条件
            boolQueryBuilder.filter(QueryBuilders.termQuery("catalog3Id", shopSearchQuery.getCatalog3Id()));
        }

        //keyword不为空
        if (StringUtils.isNotBlank(shopSearchQuery.getKeyword())) {
            boolQueryBuilder.filter(QueryBuilders.queryStringQuery(shopSearchQuery.getKeyword()).field("skuName"));
        }
        queryBuilder.withQuery(boolQueryBuilder);


        //构建排序字段
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));


        //指定高亮字段和样式
        queryBuilder.withHighlightFields(new HighlightBuilder.Field("skuName")
                .preTags("<font style='color:red'>")
                .postTags("</font>"));

        //分页查询PageRequest.of()第一个参数指的第几页(默认从0开始算的)
        //queryBuilder.withPageable(PageRequest.of(shopSearchQuery.getCurrentPage() - 1, shopSearchQuery.getPageSize()));



        //构造聚合条件,根据valueId分组统计
        /**
         * GET shop/_search
         * {
         *   "query": {
         *     "match_all": {}
         *   },
         *   "aggs": {
         *     "groupby_valueId": {
         *       "terms": {
         *         "field": "skuAttrValueList.valueId",
         *         "size": 100
         *       }
         *     }
         *   }
         *
         * }
         */
        queryBuilder.addAggregation(AggregationBuilders.terms("groupby_valueId")
                .field("skuAttrValueList.valueId")
                .size(Integer.MAX_VALUE));



        //2.执行查询
        SearchHits<SearchSkuInfo> skuInfoSearchHits = elasticsearchRestTemplate.search(queryBuilder.build(), SearchSkuInfo.class);

        //3.将SearchHits<SearchSkuInfo>转换成List<SearchSkuInfo>
        List<SearchSkuInfo> searchSkuInfoList = skuInfoSearchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

        //将SearchSkuInfo对象中的skuName属性值替换成高亮的skuName值
        skuInfoSearchHits.stream().filter(skuInfoSearchHit -> !CollectionUtils.isEmpty(skuInfoSearchHit.getHighlightFields()))
                .forEach(skuInfoSearchHit -> skuInfoSearchHit
                        .getContent().setSkuName(skuInfoSearchHit.getHighlightField("skuName")
                                .stream().collect(Collectors.joining())));

        //4.将searchSkuInfoList封装到PageResult中
        PageResult<SearchSkuInfo> pageResult = new PageResult<>();
        pageResult.setPageData(searchSkuInfoList);



        //5.设置分页相关的参数
        long totalCount = skuInfoSearchHits.getTotalHits();
        pageResult.setTotalCount(totalCount);


        /**
         * totalCount=10 pageSize=3
         * totalCount=12 pageSize=3
         */
        //long totalPage = (totalCount + shopSearchQuery.getPageSize() - 1) / shopSearchQuery.getPageSize();
        //pageResult.setTotalPage(totalPage);


        //6.取出聚合查询的结果
        ParsedTerms parsedTerms = skuInfoSearchHits.getAggregations().get("groupby_valueId");
        List<Integer> valueIds = parsedTerms.getBuckets().stream()
                .map(bucket -> Integer.parseInt(bucket.getKey() + ""))
                .collect(Collectors.toList());

        System.out.println(valueIds);
        pageResult.setValueIds(valueIds);
    }

}