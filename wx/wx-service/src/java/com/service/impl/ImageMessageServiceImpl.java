package com.service.impl;

import com.dto.wx.ReceiveDto;
import com.service.ReplyMessageService;

import java.text.MessageFormat;
import java.util.Calendar;

/**
 * Created by Admin on 2016/3/12.
 */
public class ImageMessageServiceImpl implements ReplyMessageService {
    @Override
    public String getReplyMessage(ReceiveDto receive) {
        String from = receive.getFromUserName();
        String to = receive.getToUserName();
        String msg = formatReplyMessage(to, from, 001);
        return msg;
    }

    @Override
    public String formatReplyMessage(String from, String to, Object obj) {
        String str = "<xml>\n" +
                "<ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "<FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "<CreateTime>{2}</CreateTime>\n" +
                "<MsgType><![CDATA[image]]></MsgType>\n" +
                "<Image>\n" +
                "<MediaId><![CDATA[{3}]]></MediaId>\n" +
                "</Image>\n" +
                "</xml>";
        Long time = Calendar.getInstance().getTimeInMillis();
        str =    MessageFormat.format(str, to, from, obj);
        return str;
    }
}
