package com.service.impl;

import com.dto.wx.ReceiveDto;
import com.dto.wx.ReplyDto;
import com.service.TextMessageService;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/3/11.
 *文本消息
 */
@Service("textMessageService")
public class TextMessageServiceImpl implements TextMessageService {
    @Override
    public String getSimpleText(ReceiveDto receive) {
        String from = receive.getFromUserName();
        String to = receive.getToUserName();
        String msg = ReplyDto.getTextMessage(from, to, "收到你的消息了");
        return msg;
    }
}
