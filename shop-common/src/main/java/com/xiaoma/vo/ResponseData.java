package com.xiaoma.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应组装形式
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {
    /*响应数据*/
    private T data;

    /*状态码*/
    private Integer code;

    /*提示信息*/
    private String msg;
}
