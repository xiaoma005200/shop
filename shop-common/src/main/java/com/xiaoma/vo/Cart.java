package com.xiaoma.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    /*很多购物项*/
    private List<CartItem> cartItems;

    /*购物项总数(商品类型总数)*/
    private Integer totalType;

    /*已选择商品数量总计*/
    private Integer totalNumber;

    /*商品总计*/
    private BigDecimal totalPrice;

    /*减免价格*/
    private BigDecimal reducedPrice = new BigDecimal("0");

    public Integer getTotalType() {
        return CollectionUtils.isEmpty(cartItems) ? 0 : cartItems.size();
    }

    public Integer getTotalNumber() {
       /*
       * 统计已选择的购物项的总数量
       */
        //购物车有购物项并且其中有选中的购物项
        if (!CollectionUtils.isEmpty(cartItems) && cartItems.stream().anyMatch(cartItem -> cartItem.getCheck())) {
            return cartItems.stream()
                    .filter(cartItem -> cartItem.getCheck()) //过滤掉未选中购物项,保留选中购物项
                    .map(CartItem::getNumber)
                    .reduce(Integer::sum).get();
        }
        return 0;
    }

    public BigDecimal getTotalPrice() {
        /*
         * 统计已选择的购物项的总计
         */
        //购物车有购物项并且其中有选中的购物项
        if (!CollectionUtils.isEmpty(cartItems) && cartItems.stream().anyMatch(cartItem -> cartItem.getCheck())) {
           return cartItems.stream()
                    .filter(cartItem -> cartItem.getCheck())
                    .map(CartItem::getSubtotal)
                    .reduce(BigDecimal::add)
                    .get().subtract(reducedPrice);
        }
        return new BigDecimal("0");
    }
}
