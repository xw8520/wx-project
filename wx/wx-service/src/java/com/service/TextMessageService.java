package com.service;

import com.dto.wx.ReceiveDto;

/**
 * Created by Admin on 2016/3/11.
 * 文本消息
 */
public interface TextMessageService {

    /**
     * 回复普通文本消息
     * @param receive
     * @return
     */
     String getSimpleText(ReceiveDto receive);
}
