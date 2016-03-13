package com.service.impl;

import com.dto.wx.ReceiveMsg;
import com.service.ReplyMessageService;

import java.text.MessageFormat;
import java.util.Calendar;

/**
 * Created by Admin on 2016/3/12.
 * 语音消息
 */
public class VoiceMessageServiceImpl implements ReplyMessageService {
    @Override
    public String getReplyMessage(ReceiveMsg receive) {
        String from = receive.getFromUserName();
        String to = receive.getToUserName();
        String msg = formatReplyMessage(from, to, 001);
        return msg;
    }

    @Override
    public String formatReplyMessage(String from, String to, Object obj) {
        String str = "<xml>\n" +
                "<ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "<FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "<CreateTime>{2}</CreateTime>\n" +
                "<MsgType><![CDATA[voice]]></MsgType>\n" +
                "<Voice>\n" +
                "<MediaId><![CDATA[{3}]]></MediaId>\n" +
                "</Voice>\n" +
                "</xml>";
        Long time = Calendar.getInstance().getTimeInMillis();
        str = MessageFormat.format(str, to, from, time, obj);
        return str;
    }
}
