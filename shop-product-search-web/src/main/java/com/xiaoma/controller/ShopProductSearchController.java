package com.xiaoma.controller;

import com.xiaoma.feign.ShopSearchClient;
import com.xiaoma.pojo.SearchSkuInfo;
import com.xiaoma.vo.PageResult;
import com.xiaoma.vo.ShopSearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopProductSearchController {

    @Autowired
    ShopSearchClient shopSearchClient;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/list")
    public String list(ShopSearchQuery shopSearchQuery, Model model){
        PageResult<SearchSkuInfo> pageResult = shopSearchClient.searchByParams(shopSearchQuery);
        model.addAttribute("pageResult",pageResult);
        model.addAttribute("shopSearchQuery",shopSearchQuery);
        return "list";
    }
}
