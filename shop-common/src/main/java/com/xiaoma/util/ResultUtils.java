package com.xiaoma.util;

import com.xiaoma.constant.OrderStatusEnum;
import com.xiaoma.vo.ResponseData;

import java.lang.reflect.Method;

/**
 * 通用的构造返回的数据
 * data:填充的数据
 * code:状态码
 * msg:提示信息
 */
public class ResultUtils {
    /**
     *
     * @param anEnum 任意一个枚举,要求自定义枚举中需要含有getCode和getMsg方法
     * @param data 数据
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> result(Enum anEnum, T data) {
        try {
            ResponseData<T> responseData = new ResponseData<>();
            responseData.setData(data);

            Method getCodeMethod = anEnum.getClass().getMethod("getCode");
            Method getMsgMethod = anEnum.getClass().getMethod("getMsg");
            if (getCodeMethod != null) {
                responseData.setCode((Integer)getCodeMethod.invoke(anEnum, null));
            }

            if (getMsgMethod != null) {
                responseData.setMsg((String)getMsgMethod.invoke(anEnum, null));
            }

            return responseData;

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData<>();
        }
    }

    public static void main(String[] args) {
        System.out.println(ResultUtils.result(OrderStatusEnum.LOCKED_STOCK_SUCCESS, "def"));
    }
}
