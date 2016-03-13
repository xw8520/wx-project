package com.service.impl;

import com.dto.wx.ReceiveMsg;
import com.service.ReplyMessageService;

import java.text.MessageFormat;
import java.util.Calendar;

/**
 * Created by Admin on 2016/3/11.
 * 文本消息
 */
public class TextMessageServiceImpl implements ReplyMessageService {

    @Override
    public String getReplyMessage(ReceiveMsg receive) {
        String from = receive.getFromUserName();
        String to = receive.getToUserName();
        String msg = formatReplyMessage(from, to, "收到你的消息了");
        return msg;
    }

    @Override
    public String formatReplyMessage(String from, String to, Object obj) {
        String str = "<xml>\n" +
                "<ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "<FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "<CreateTime>{2}</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[{3}]]></Content>\n" +
                "</xml>";

        Long time = Calendar.getInstance().getTimeInMillis();
        str = MessageFormat.format(str, from, to, time, obj);
        return str;
    }
}
