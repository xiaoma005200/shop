package com.xiaoma.controller;

import com.xiaoma.feign.ProductClient;
import com.xiaoma.feign.ShopSearchClient;
import com.xiaoma.pojo.BaseAttrInfo;
import com.xiaoma.pojo.BaseAttrValue;
import com.xiaoma.pojo.SearchSkuInfo;
import com.xiaoma.vo.PageResult;
import com.xiaoma.vo.ShopSearchCrumbs;
import com.xiaoma.vo.ShopSearchQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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
    public String index() {
        return "index";
    }

    @GetMapping("/list")
    public String list(ShopSearchQuery shopSearchQuery, Model model) {
        // 1.根据页面参数查询
        PageResult<SearchSkuInfo> pageResult = shopSearchClient.searchByParams(shopSearchQuery);

        // 2.将pageResult和shopSearchQuery设置到model域
        model.addAttribute("pageResult", pageResult);
        model.addAttribute("shopSearchQuery", shopSearchQuery);

        // 3.根据valueIds查询所有的属性名和属性值
        List<BaseAttrInfo> baseAttrInfoList = productClient.getAttrByValueIds(pageResult.getValueIds());

        List<String> deleteValueIds = shopSearchQuery.getValueId();//存放页面的所有的valueId 例如:[252]
        //构造面包屑导航
        ArrayList<ShopSearchCrumbs> shopSearchCrumbsList = new ArrayList<>();
        /**
         * 第一种方案:查询mysql数据库根据valueId获取对应的valueName和attrName,这种方式频繁与数据库交换
         * 第二种方案:从已有数据(baseAttrInfoList)中筛选出我们需要的valueName和attrName
         */
        if (!CollectionUtils.isEmpty(deleteValueIds)) {//说明用户已经点击某个属性值
            deleteValueIds.forEach(deleteValueId -> {
                ShopSearchCrumbs shopSearchCrumbs = new ShopSearchCrumbs();
                /*设置attrName,valueName,urlParams*/
                shopSearchCrumbs.setAttrName(baseAttrInfoList.stream().filter(baseAttrInfo -> baseAttrInfo.getAttrValueList().stream()
                        .anyMatch(baseAttrValue -> (baseAttrValue.getId() + "").equals(deleteValueId))).findFirst().get().getAttrName());

                shopSearchCrumbs.setValueName(baseAttrInfoList.stream().flatMap(baseAttrInfo -> baseAttrInfo.getAttrValueList().stream())
                        .filter(baseAttrValue -> (baseAttrValue.getId() + "").equals(deleteValueId))
                        .findFirst().get().getValueName());

                shopSearchCrumbs.setUrlParams(getUrlParams(shopSearchQuery,deleteValueId));

                shopSearchCrumbsList.add(shopSearchCrumbs);
            });
            model.addAttribute("attrValueSelectedList", shopSearchCrumbsList);
        }

        // 删除用户选择的属性值所在属性组
        if (!CollectionUtils.isEmpty(deleteValueIds)) {//说明用户已经点击某个属性值
            baseAttrInfoList.removeIf(baseAttrInfo -> {
                List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
                List<String> valueIds = attrValueList.stream().map(baseAttrValue -> baseAttrValue.getId() + "").collect(Collectors.toList());
                //制作一个deleteValueIds的副本
                List<String> copyDeleteValueIds = deleteValueIds.stream().collect(Collectors.toList());
                //判断deleteValueIds与valueIds是否有交集
                copyDeleteValueIds.retainAll(valueIds);
                return copyDeleteValueIds.size() > 0;
            });
        }
        model.addAttribute("attrList", baseAttrInfoList);

        // 4.从shopSearchQuery取出参数进行拼接
        String urlParams = getUrlParams(shopSearchQuery);
        model.addAttribute("urlParams", urlParams);

        return "list";
    }

    /**
     * 4.从shopSearchQuery取出参数进行拼接
     *
     * @param shopSearchQuery shopSearchQuery
     * @return urlParams
     */
    private String getUrlParams(ShopSearchQuery shopSearchQuery,String...deleteValueIds) {
        String keyword = shopSearchQuery.getKeyword();
        Long catalog3Id = shopSearchQuery.getCatalog3Id();
        List<String> valueIds = shopSearchQuery.getValueId();

        StringBuilder urlParams = new StringBuilder();

        if (StringUtils.isNotBlank(keyword)) {
            if (urlParams.length() != 0) {
                urlParams.append("&");
            }
            urlParams.append("keyword=" + keyword);
        }

        if (catalog3Id != null) {
            if (urlParams.length() != 0) {
                urlParams.append("&");
            }
            urlParams.append("catalog3Id=" + catalog3Id);
        }

        if (!CollectionUtils.isEmpty(valueIds)) {
            if (urlParams.length() != 0) {
                urlParams.append("&");
            }
            if (deleteValueIds.length == 0) {
                /*&valueId=xxx&valueId=xxx,...*/
                /*247, 238, 235    valueId=246&valueId=238,....*/
                urlParams.append(valueIds.stream().map(valueId -> "valueId=" + valueId)
                        .collect(Collectors.joining("&")));
            } else {
                // 构造面包屑的valueId参数,我们需要删除自身的valueId
                String valueIdStr = valueIds.stream()
                        .filter(valueId -> !valueId.equals(deleteValueIds[0]))//排除自身valueId
                        .map(valueId -> "valueId=" + valueId)
                        .collect(Collectors.joining("&"));
                // urlParams=catalog3Id=486&
                int lastIndex = urlParams.lastIndexOf("&");
                if (StringUtils.isBlank(valueIdStr) && lastIndex != -1) {
                    urlParams.deleteCharAt(lastIndex);
                } else {
                    urlParams.append(valueIdStr);
                }
            }

        }

        return urlParams.toString();
    }

}
