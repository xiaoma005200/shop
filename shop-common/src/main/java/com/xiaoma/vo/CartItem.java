package com.xiaoma.vo;

import com.xiaoma.pojo.SkuSaleAttrValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CartItem {
    /*商品id*/
    private Long skuId;

    /*购物项选中状态*/
    private Boolean check;

    /*商品名称*/
    private String skuName;

    /*默认显示图片*/
    private String skuDefaultImg;

    /*商品单价*/
    private BigDecimal price;

    /*购买数量*/
    private Integer number;

    /*小计*/
    private BigDecimal subtotal;

    /*销售属性*/
    private List<SkuSaleAttrValue> skuSaleAttrValueList;

    /*获取小计的时候自动计算*/
    public BigDecimal getSubtotal() {
       return price != null ? price.multiply(new BigDecimal(number + "")) : new BigDecimal("0");
    }
}
