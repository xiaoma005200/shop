package com.xiaoma.feign;

import com.xiaoma.vo.PayVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("shop-product-order-web")
public interface ShopOrderClient {

    @GetMapping("/shop/order/getPayInfoByOrderSn")
    @ResponseBody
    PayVO getPayInfoByOrderSn(@RequestParam("orderSn") String orderSn);

    @GetMapping("/shop/order/updateOrderStatus")
    void updateOrderStatus(@RequestParam("orderSn") String orderSn,@RequestParam("code") Integer code);
}
