package com.service.impl;

import com.dto.wx.NewsMessageItem;
import com.dto.wx.ReceiveMsg;
import com.service.WxMsgReplyService;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Admin on 2016/3/19.
 * 格式化微信消息
 */
public class WxMsgReplyServiceImpl implements WxMsgReplyService {
    @Override
    public String getTextMsg(ReceiveMsg receive, String msg) {
        String from = receive.getFromUserName();
        String to = receive.getToUserName();
        String str = "<xml>\n" +
                "<ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "<FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "<CreateTime>{2}</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[{3}]]></Content>\n" +
                "</xml>";

        Long time = Calendar.getInstance().getTimeInMillis();
        return MessageFormat.format(str, from, to, String.valueOf(time), msg);
    }

    @Override
    public String getImageMsg(ReceiveMsg receive, String mediaId) {
        String msg = "<xml>\n" +
                "<ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "<FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "<CreateTime>{2}</CreateTime>\n" +
                "<MsgType><![CDATA[image]]></MsgType>\n" +
                "<Image>\n" +
                "<MediaId><![CDATA[{3}]]></MediaId>\n" +
                "</Image>\n" +
                "</xml>";
        Long time = Calendar.getInstance().getTimeInMillis();
        return MessageFormat.format(msg, receive.getToUserName(), receive.getFromUserName(), String.valueOf(time), mediaId);
    }

    @Override
    public String getVoiceMsg(ReceiveMsg receive, String mediaId) {
        String from = receive.getFromUserName();
        String to = receive.getToUserName();
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
        str = MessageFormat.format(str, to, from, String.valueOf(time), mediaId);
        return str;
    }

    @Override
    public String getNewsMsg(ReceiveMsg receive, List<NewsMessageItem> list) {
        String from = receive.getFromUserName();
        String to = receive.getToUserName();
        int count = list.size();
        String msg = "<xml>\n" +
                "<ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "<FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "<CreateTime>{2}</CreateTime>\n" +
                "<MsgType><![CDATA[news]]></MsgType>\n" +
                "<ArticleCount>{3}</ArticleCount>\n" +
                "<Articles>{4}" +
                "</Articles>" +
                "</xml> ";

        String item = "<item>\n" +
                "<Title><![CDATA[%s]]></Title> \n" +
                "<Description><![CDATA[%s]]></Description>\n" +
                "<PicUrl><![CDATA[%s]]></PicUrl>\n" +
                "<Url><![CDATA[%s]]></Url>\n" +
                "</item>";
        StringBuffer buffer = new StringBuffer();
        for (NewsMessageItem s : list) {
            buffer.append(String.format(item, s.getTitle(), s.getDesc(), s.getPicUrl(), s.getUrl()));
        }
        Long time = Calendar.getInstance().getTimeInMillis();
        msg = MessageFormat.format(msg, to, from, String.valueOf(time), count, buffer.toString());
        return msg;
    }

    @Override
    public String getVideoMsg(ReceiveMsg receive, String mediaId, String title, String desc) {
        String str = "<xml>\n" +
                "<ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "<FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "<CreateTime>{2}</CreateTime>\n" +
                "<MsgType><![CDATA[video]]></MsgType>\n" +
                "<Video>\n" +
                "<MediaId><![CDATA[{3}]]></MediaId>\n" +
                "<Title><![CDATA[{4}]]></Title>\n" +
                "<Description><![CDATA[{5}]]></Description>\n" +
                "</Video> \n" +
                "</xml>";
        Long time = Calendar.getInstance().getTimeInMillis();
        return MessageFormat.format(str, receive.getFromUserName(), receive.getToUserName(),
                mediaId, String.valueOf(time), title, desc);

    }

    @Override
    public String getMusicMsg(ReceiveMsg receive, String mediaId, String title,
                              String desc, String misucUrl, String hqUrl) {
        String str = "<xml>\n" +
                "<ToUserName><![CDATA[{0}]]></ToUserName>\n" +
                "<FromUserName><![CDATA[{1}]]></FromUserName>\n" +
                "<CreateTime>{2}</CreateTime>\n" +
                "<MsgType><![CDATA[music]]></MsgType>\n" +
                "<Music>\n" +
                "<Title><![CDATA[{3}]]></Title>\n" +
                "<Description><![CDATA[{4}]]></Description>\n" +
                "<MusicUrl><![CDATA[{5}]]></MusicUrl>\n" +
                "<HQMusicUrl><![CDATA[{6}]]></HQMusicUrl>\n" +
                "<ThumbMediaId><![CDATA[{7}]]></ThumbMediaId>\n" +
                "</Music>\n" +
                "</xml>";
        Long time = Calendar.getInstance().getTimeInMillis();
        return MessageFormat.format(str, receive.getFromUserName(), receive.getToUserName(),
                String.valueOf(time), title, desc, misucUrl, hqUrl, mediaId);
    }
}
