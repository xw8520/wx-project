package com.service.api;

import com.wxconfig.ReceiveMsg;

/**
 * Created by Admin on 2016/3/19.
 */
public interface WxEventService {
    /**
     * 微信事件处理
     * @return
     */
    String wxEventHandler(ReceiveMsg receive);

    /**
     * 关注
     * @param receive
     * @return
     */
    String subscribe(ReceiveMsg receive);

    /**
     * 取消关注
     * @param receive
     * @return
     */
    String unsubscribe(ReceiveMsg receive);
}
