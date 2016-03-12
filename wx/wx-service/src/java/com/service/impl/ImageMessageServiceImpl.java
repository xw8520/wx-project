package com.service.impl;

import com.dto.wx.ReceiveDto;
import com.service.ReplyMessageService;

import java.util.Calendar;

/**
 * Created by Admin on 2016/3/12.
 */
public class ImageMessageServiceImpl implements ReplyMessageService {
    @Override
    public String getReplyMessage(ReceiveDto receive) {
        String from = receive.getFromUserName();
        String to = receive.getToUserName();
        String msg = getImageMessage(to, from, 001);
        return msg;
    }

    /**
     * 获取图片消息
     *
     * @param from
     * @param to
     * @param id
     * @return
     */
    public String getImageMessage(String from, String to, int id) {
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
