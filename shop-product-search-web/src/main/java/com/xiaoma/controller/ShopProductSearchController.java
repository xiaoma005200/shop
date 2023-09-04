package com.xiaoma.controller;

import com.xiaoma.feign.ProductClient;
import com.xiaoma.feign.ShopSearchClient;
import com.xiaoma.pojo.BaseAttrInfo;
import com.xiaoma.pojo.SearchSkuInfo;
import com.xiaoma.vo.PageResult;
import com.xiaoma.vo.ShopSearchQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/shop")
public class ShopProductSearchController {

    @Autowired
    ShopSearchClient shopSearchClient;

    @Autowired
    ProductClient productClient;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/list")
    public String list(ShopSearchQuery shopSearchQuery, Model model){
        // 1.根据页面参数查询
        PageResult<SearchSkuInfo> pageResult = shopSearchClient.searchByParams(shopSearchQuery);

        // 2.将pageResult和shopSearchQuery设置到model域
        model.addAttribute("pageResult",pageResult);
        model.addAttribute("shopSearchQuery",shopSearchQuery);

        // 3.根据valueIds查询所有的属性名和属性值
        List<BaseAttrInfo> baseAttrInfoList = productClient.getAttrByValueIds(pageResult.getValueIds());
        model.addAttribute("attrList",baseAttrInfoList);

        // 4.从shopSearchQuery取出参数进行拼接
        String urlParams = getUrlParams(shopSearchQuery);
        model.addAttribute("urlParams",urlParams);

        return "list";
    }

    /**
     * 4.从shopSearchQuery取出参数进行拼接
     * @param shopSearchQuery
     * @return
     */
    private String getUrlParams(ShopSearchQuery shopSearchQuery) {
        String keyword = shopSearchQuery.getKeyword();
        Long catalog3Id = shopSearchQuery.getCatalog3Id();
        List<String> valueIds = shopSearchQuery.getValueId();

        StringBuilder urlParams = new StringBuilder();
        if (StringUtils.isNotBlank(keyword)) {
            if(urlParams.length() != 0){
                urlParams.append("&");
            }
            urlParams.append("keyword" + keyword);
        }

        if (catalog3Id != null) {
            if(urlParams.length() != 0){
                urlParams.append("&");
            }
            urlParams.append("catalog3Id" + catalog3Id);
        }

        if (!CollectionUtils.isEmpty(valueIds)) {
            if(urlParams.length() != 0){
                urlParams.append("&");
            }
            /*  247,238  valueId=247,valueId=238  */
            urlParams.append(valueIds.stream().map(valueId -> "valueId" + valueId)
                    .collect(Collectors.joining("&")));
        }

        return urlParams.toString();
    }
}
