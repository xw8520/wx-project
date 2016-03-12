package com.service;

import com.dto.wx.ReceiveDto;

/**
 * Created by Admin on 2016/3/12.
 * 消息处理
 */
public class WxMessageContext {
    private ReplyMessageService msgService;

    public WxMessageContext(ReplyMessageService msgService) {
        this.msgService = msgService;
    }

    public String getReplyMessage(ReceiveDto receive) {
        return msgService.getReplyMessage(receive);
    }
}
