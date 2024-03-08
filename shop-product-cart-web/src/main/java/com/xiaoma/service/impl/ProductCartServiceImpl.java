package com.xiaoma.service.impl;

import com.xiaoma.feign.ProductClient;
import com.xiaoma.pojo.SkuInfo;
import com.xiaoma.service.ProductCartService;
import com.xiaoma.util.RedisUtils;
import com.xiaoma.vo.Cart;
import com.xiaoma.vo.CartItem;
import com.xiaoma.vo.UserInfo;
import com.google.gson.Gson;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.lucene.util.CollectionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
@Accessors(chain = true)
public class ProductCartServiceImpl implements ProductCartService {
    @Autowired
    ProductClient productClient;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public CartItem addToCart(UserInfo userInfo, Long skuId, Integer num) {
        //1.根据登录状态来赋值不同cartKey
        String cartKey = userInfo.getUserId() != null ? "cart:" + userInfo.getUserId() : "cart:" + userInfo.getUserKey();

        //2.组装购物项数据
        //判断购物车中是否已经存在当前sku
        Object value = redisUtils.hget(cartKey, skuId + "");
        CartItem cartItem = null;
        if (value == null) {
            cartItem = new CartItem();
            //直接添加到购物车
            SkuInfo skuInfo = productClient.findBySkuInfoId(skuId.intValue());
            BeanUtils.copyProperties(skuInfo, cartItem);
            cartItem.setSkuId(skuId);
            cartItem.setCheck(true);//新添加的商品默认都勾选
            cartItem.setNumber(num);
            cartItem.setPrice(new BigDecimal(skuInfo.getPrice() + ""));
        } else {
            //再次添加同一个sku,针对同一个sku叠加购买数量
            cartItem = new Gson().fromJson(value.toString(), CartItem.class);
            cartItem.setNumber(cartItem.getNumber() + num);//叠加数量
            redisUtils.lrem(cartKey + ":sorted", skuId + "");
        }

        //3.将购物项数据存储redis对应的购物车中
        redisUtils.hset(cartKey, skuId + "", new Gson().toJson(cartItem));
        redisUtils.lpush(cartKey + ":sorted", skuId + "");

        return cartItem;
    }

    @Override
    public Cart getUserCart(UserInfo userInfo) {
        Cart cart = new Cart();

        //临时用户购物处理
        if (userInfo.getUserId() == null) {
            //从redis中根据用户的key获取所有的购物项
            cart.setCartItems(getCartItemsByUser("cart:" + userInfo.getUserKey()));

        } else {//登录用户购物车处理(需要考虑将临时用户购物车中的购物项合并到登录用户的购物车中)
            //获取临时用户的购物车
            List<String> cartItemJsons = redisUtils.hvals("cart:" + userInfo.getUserKey());
            if (!CollectionUtils.isEmpty(cartItemJsons)) {
                //合并购物项
                cartItemJsons.forEach(cartItemJson -> {
                    CartItem cartItem = new Gson().fromJson(cartItemJson, CartItem.class);

                    //将临时用户购物车中的购物项加入到登录用户的购物车中
                    addToCart(userInfo, cartItem.getSkuId(), cartItem.getNumber());
                });

                //合并sorted(skuId集合)
                List<String> tempSortedSkuIds = redisUtils.listAll("cart:" + userInfo.getUserKey() + ":sorted");//130 129 128
                /**
                 * 第一次循环:
                 *    tempSortedSkuId=128
                 *    userSortedSkuIds:133 132 131 130
                 *     redis:128 133 132 131 130
                 *  第二次循环:
                 *      tempSortedSkuId=129
                 *       userSortedSkuIds:128 133 132 131 130
                 *         redis:129 128 133 132 131 130
                 *
                 *  *  第二次循环:
                 *       tempSortedSkuId=130
                 *        userSortedSkuIds:129 128 133 132 131 130
                 *         redis:130 129 128 133 132 131
                 */
                for (int i = tempSortedSkuIds.size() - 1; i >= 0; i--) {
                    String tempSortedSkuId = tempSortedSkuIds.get(i);

                    List<String> userSortedSkuIds = redisUtils.listAll("cart:" + userInfo.getUserId() + ":sorted");
                    if (userSortedSkuIds.contains(tempSortedSkuId)) {
                        redisUtils.lrem("cart:" + userInfo.getUserId() + ":sorted", tempSortedSkuId);
                    }
                    redisUtils.lpush("cart:" + userInfo.getUserId() + ":sorted", tempSortedSkuId);

                }

                //清空临时购物车和临时sorted
                //redisUtils.del("cart:" + userInfo.getUserKey());
                redisUtils.del("cart:" + userInfo.getUserKey(), "cart:" + userInfo.getUserKey() + ":sorted");
            }

            //重新获取下登录用户购物车(临时购物车购物项+登录购物车的购物项)
            cart.setCartItems(getCartItemsByUser("cart:" + userInfo.getUserId()));
        }

        return cart;
    }

    @Override
    public void checkCartItem(UserInfo userInfo, Long skuId, Integer check) {
        // 1.获取对应用户的cartKey
        String cartKey = userInfo.getUserId() != null ? "cart:" + userInfo.getUserId() : "cart:" + userInfo.getUserKey();

        // 2.根据cartKey+skuId取出对应的购物项
        CartItem cartItem = getCartItemBySkuId(cartKey, skuId + "");

        //3.设置购物项选中状态
        cartItem.setCheck(check == 1);

        //4.将购物项重新设置回redis
        redisUtils.hset(cartKey, skuId + "", new Gson().toJson(cartItem));
    }

    @Override
    public void changeCartItemNumber(UserInfo userInfo, Long skuId, Integer number) {
        String cartKey = userInfo.getUserId() != null ? "cart:" + userInfo.getUserId() : "cart:" + userInfo.getUserKey();

        CartItem cartItem = getCartItemBySkuId(cartKey, skuId + "");

        //3.设置购物项购买数量
        cartItem.setNumber(number);

        //4.将购物项重新设置回redis
        redisUtils.hset(cartKey, skuId + "", new Gson().toJson(cartItem));
    }

    @Override
    public void deleteCartItemBySkuId(UserInfo userInfo, Long skuId) {
        String cartKey = userInfo.getUserId() != null ? "cart:" + userInfo.getUserId() : "cart:" + userInfo.getUserKey();

        //删除购物项
        redisUtils.hdel(cartKey,skuId+"");
        //删除排序sorted中该skuId
        redisUtils.lrem(cartKey+":sorted", skuId+ "");
    }

    @Override
    public List<CartItem> getCheckedCartItems(UserInfo userInfo) {
        Cart userCart = getUserCart(userInfo);
        List<CartItem> cartItems = userCart.getCartItems().stream()
                .filter(cartItem -> cartItem.getCheck()) //过滤掉没有选中的购物项
                .map(cartItem -> cartItem.setPrice(productClient.findPriceBySkuId(cartItem.getSkuId())))
                .collect(Collectors.toList());
        return cartItems;
    }


    /**
     * 根据cartKey+skuId获取指定的购物项
     * @param skuId
     * @return
     */
    private CartItem getCartItemBySkuId(String cartKey, String skuId) {
        CartItem cartItem = new Gson().fromJson(redisUtils.hget(cartKey, skuId + "") + "", CartItem.class);
        return cartItem;
    }


    private List<CartItem> getCartItemsByUser(String cartKey) {
   /*     List<String> cartItemJsons = redisUtils.hvals(cartKey);
        if (!CollectionUtils.isEmpty(cartItemJsons)) {
            List<CartItem> cartItems = cartItemJsons.stream()
                    .map(cartItemJson -> new Gson().fromJson(cartItemJson, CartItem.class))
                    .collect(Collectors.toList());
            return cartItems;
        }
        return null;*/

        List<String> sortedSkuIds = redisUtils.listAll(cartKey + ":sorted");
        if (!CollectionUtils.isEmpty(sortedSkuIds)) {
            List<CartItem> sortedCartItems = sortedSkuIds.stream()
                    .map(sortedSkuId -> new Gson().fromJson(redisUtils.hget(cartKey, sortedSkuId) + "", CartItem.class))
                    .collect(Collectors.toList());
            return sortedCartItems;
        }
        return null;
    }
}
