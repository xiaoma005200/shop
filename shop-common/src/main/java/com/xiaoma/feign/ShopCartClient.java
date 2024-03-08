package com.xiaoma.feign;

import com.xiaoma.vo.CartItem;
import com.xiaoma.vo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient("shop-product-cart-web")
public interface ShopCartClient {

    @PostMapping("/shop/cart/getCheckedCartItems")
    @ResponseBody
    List<CartItem> getCheckedCartItems(@RequestBody UserInfo userInfo);
}
