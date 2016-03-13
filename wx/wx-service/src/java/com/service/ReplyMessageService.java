package com.service;

import com.dto.wx.ReceiveDto;

/**
 * Created by Admin on 2016/3/11.
 * 文本消息
 */
public interface ReplyMessageService {

    /**
     * 回复普通文本消息
     * @param receive
     * @return
     */
     String getReplyMessage(ReceiveDto receive) throws Exception;

    String formatReplyMessage(String from, String to, Object obj) throws Exception;
}
