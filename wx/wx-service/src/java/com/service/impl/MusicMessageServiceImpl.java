package com.service.impl;

import com.dto.wx.ReceiveDto;
import com.service.ReplyMessageService;

/**
 * Created by Admin on 2016/3/13.
 */
public class MusicMessageServiceImpl implements ReplyMessageService {
    @Override
    public String getReplyMessage(ReceiveDto receive) {
        String from = receive.getFromUserName();
        String to = receive.getToUserName();
        return null;
    }

    @Override
    public String formatReplyMessage(String from, String to, Object obj) {
        return null;
    }
}
