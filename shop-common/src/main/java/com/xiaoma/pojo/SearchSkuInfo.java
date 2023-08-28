package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "shop")
public class SearchSkuInfo {

    /*id*/
    @Id
    private Long id;

    /*价格*/
    @Field
    private Double price;

    /*sku名称*/
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String skuName;

    /*sku描述*/
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String skuDesc;


    /*三级分类id*/
    @Field
    private Long catalog3Id;


    /*默认图片*/
    @Field
    private String skuDefaultImg;


    /*将来会封装sku对应的平台属性以及属性值*/
    @Field
    private List<SkuAttrValue> skuAttrValueList;


}
