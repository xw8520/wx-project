package com.dto.wx;

import java.io.Serializable;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.Calendar;

/**
 * Created by Admin on 2016/3/11.
 * 回复消息
 */
public class ReplyDto implements Serializable {

    /**
     * 获取文本消息
     *
     * @param from
     * @param to
     * @param msg
     * @return
     */
    public static String getTextMessage(String from, String to, String msg) {
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

    /**
     * 获取图片消息
     *
     * @param from
     * @param to
     * @param id
     * @return
     */
    public static String getImageMessage(String from, String to, int id) {
        String str = "<xml>\n" +
                "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
                "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
                "<CreateTime>%d</CreateTime>\n" +
                "<MsgType><![CDATA[image]]></MsgType>\n" +
                "<Image>\n" +
                "<MediaId><![CDATA[%d]]></MediaId>\n" +
                "</Image>\n" +
                "</xml>";
        Long time = Calendar.getInstance().getTimeInMillis();
        str = String.format(str, from, to, time, id);
        return str;
    }
}
