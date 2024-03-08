package com.xiaoma.service;

import com.xiaoma.vo.AliPayAsyncNotifyVo;

public interface ShopAlipayService {

    /**
     * 根据异步通知的参数进行相关的业务处理：例如修改订单状态，存储交易流水等
     * @param aliPayAsyncNotifyVo
     * @return
     */
    String updateInfoByPayResult(AliPayAsyncNotifyVo aliPayAsyncNotifyVo);
}
