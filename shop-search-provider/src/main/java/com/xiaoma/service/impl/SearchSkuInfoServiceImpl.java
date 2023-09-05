package com.xiaoma.service.impl;

import com.xiaoma.pojo.SearchSkuInfo;
import com.xiaoma.service.SearchSkuInfoService;
import com.xiaoma.vo.PageResult;
import com.xiaoma.vo.ShopSearchQuery;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchSkuInfoServiceImpl implements SearchSkuInfoService {
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public PageResult<SearchSkuInfo> searchByParams(ShopSearchQuery shopSearchQuery) {
        // 1.通过NativeSearchQueryBuilder和BoolQueryBuilder(布尔查询)构造整个搜索条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        //******************使用BoolQueryBuilder构造布尔查询条件*********************
        // 三级分类Id参数
        if (shopSearchQuery.getCatalog3Id()!=null) {//不为空才构建查询条件
            boolQueryBuilder.filter(QueryBuilders.termQuery("catalog3Id", shopSearchQuery.getCatalog3Id()));
        }

        // keyword参数
        if (StringUtils.isNotBlank(shopSearchQuery.getKeyword())) {
            boolQueryBuilder.filter(QueryBuilders.queryStringQuery(shopSearchQuery.getKeyword()).field("skuName"));
        }
        //根据valueId查询
        /**
        GET shop/_search
            {
              "query": {
                "bool": {
                  "filter": [
                    {
                      "term": {
                        "skuAttrValueList.valueId": "252"
                      }

                    },{
                      "term": {
                      "skuAttrValueList.valueId": "247"
                      }
                    }
                  ]

                }
              }
            }
        */
        if (!CollectionUtils.isEmpty(shopSearchQuery.getValueId())) {
            shopSearchQuery.getValueId().forEach(valueId ->
                    boolQueryBuilder.filter(QueryBuilders.termQuery("skuAttrValueList.valueId",valueId)));
        }
        queryBuilder.withQuery(boolQueryBuilder);

        // 构建排序字段
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));


        // 指定高亮字段和样式
        queryBuilder.withHighlightFields(new HighlightBuilder.Field("skuName")
                .preTags("<font style='color:red'>")
                .postTags("</font>"));

        // 分页查询PageRequest.of()第一个参数只得是第几页(默认从第0页开始)
        queryBuilder.withPageable(PageRequest.of(shopSearchQuery.getCurrentPage() - 1, shopSearchQuery.getPageSize()));

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

        // 2.执行查询
        SearchHits<SearchSkuInfo> skuInfoSearchHits = elasticsearchRestTemplate.search(queryBuilder.build(), SearchSkuInfo.class);

        // 3.将SearchHits<SearchSkuInfo>转换成List<SearchSkuInfo>
        List<SearchSkuInfo> searchSkuInfoList = skuInfoSearchHits.stream()
                .map(skuInfoSearchHit -> skuInfoSearchHit.getContent())
                .collect(Collectors.toList());

        // 将SearchSkuInfo对象中的skuName属性值替换成高亮的skuName值,有高亮字段再替换,没有高亮字段不替换
        skuInfoSearchHits.stream().filter(skuInfoSearchHit -> !CollectionUtils.isEmpty(skuInfoSearchHit.getHighlightFields()))
                .forEach(skuInfoSearchHit -> skuInfoSearchHit
                        .getContent().setSkuName(skuInfoSearchHit.getHighlightField("skuName")
                                .stream().collect(Collectors.joining())));

        // 4.将searchSkuInfoList封装到PageResult
        PageResult<SearchSkuInfo> pageResult = new PageResult<>();
        pageResult.setPageData(searchSkuInfoList);

        // 5.设置分页相关参数
        long totalCount = skuInfoSearchHits.getTotalHits();
        pageResult.setTotalCount(totalCount);
        /**
         * totalCount=10 pageSize=3 totalPage=4
         * totalCount=12 pageSize=3 totalPage=4
         */
        long totalPage = (totalCount + shopSearchQuery.getPageSize() - 1) / shopSearchQuery.getPageSize();
        pageResult.setTotalPage(totalPage);

        //6.取出聚合查询的结果
        ParsedTerms parsedTerms = skuInfoSearchHits.getAggregations().get("groupby_valueId");
        List<Integer> valueIds = parsedTerms.getBuckets().stream()
                .map(bucket -> Integer.parseInt(bucket.getKey() + ""))
                .collect(Collectors.toList());
        pageResult.setValueIds(valueIds);

        return pageResult;
    }
}
