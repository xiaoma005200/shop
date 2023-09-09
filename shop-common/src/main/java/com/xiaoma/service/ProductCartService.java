package com.xiaoma.service;

import com.xiaoma.vo.Cart;
import com.xiaoma.vo.CartItem;
import com.xiaoma.vo.UserInfo;

import java.util.List;

/**
 * 购物车接口
 */
public interface ProductCartService {

    /**
     * 将一个sku添加到购物车
     * @param userInfo
     * @param skuId
     * @param num
     * @return
     */
    CartItem addToCart(UserInfo userInfo, Long skuId, Integer num);

    /**
     * 根据用户状态获取对应的购物车
     * @param userInfo
     * @return
     */
    Cart getUserCart(UserInfo userInfo);

    /**
     * 根据skuId改变购物车中购物项的选中状态
     * @param userInfo
     * @param skuId
     * @param check
     */
    void checkCartItem(UserInfo userInfo, Long skuId, Integer check);


    /**
     * 根据skuId改变购物车中购物项的购买数量
     * @param userInfo
     * @param skuId
     * @param number
     */
    void changeCartItemNumber(UserInfo userInfo, Long skuId, Integer number);


    /**
     * 根据cartKey+skuId删除某个购物项
     * @param skuId
     */
    void deleteCartItemBySkuId(UserInfo userInfo,Long skuId);


    /**
     * 获取购物车所有选中的购物项进行结算
     * @param userInfo
     * @return
     */
    //List<CartItem> getCheckedCartItems(UserInfo userInfo);
}
