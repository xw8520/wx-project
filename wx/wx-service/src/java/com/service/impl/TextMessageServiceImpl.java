package com.service.impl;

import com.dto.wx.ReceiveDto;
import com.service.ReplyMessageService;

import java.text.MessageFormat;
import java.util.Calendar;

/**
 * Created by Admin on 2016/3/11.
 *文本消息
 */
public class TextMessageServiceImpl implements ReplyMessageService {

    @Override
    public String getReplyMessage(ReceiveDto receive) {
        String from = receive.getFromUserName();
        String to = receive.getToUserName();
        String msg = getTextMessage(from, to, "收到你的消息了");
        return msg;
    }

    /**
     * 获取文本消息
     * @param from
     * @param to
     * @param msg
     * @return
     */
    public String getTextMessage(String from, String to, String msg) {
        String str = "<xml>\n" +
                "<ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "<FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "<CreateTime>{2}</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[{3}]]></Content>\n" +
                "</xml>";

        Long time = Calendar.getInstance().getTimeInMillis();
        MessageFormat format = new MessageFormat(str);
        str = format.format(str, from, to, time, msg);
        return str;
    }
}
