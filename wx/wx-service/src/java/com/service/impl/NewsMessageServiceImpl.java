package com.service.impl;

import com.dto.wx.NewsMessageItem;
import com.dto.wx.ReceiveMsg;
import com.service.ReplyMessageService;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Admin on 2016/3/13.
 */
public class NewsMessageServiceImpl implements ReplyMessageService {
    @Override
    public String getReplyMessage(ReceiveMsg receive) throws Exception {
        String from = receive.getFromUserName();
        String to = receive.getToUserName();
        List<NewsMessageItem> items = new ArrayList<>();
        items.add(new NewsMessageItem("测试标题", "测试图文消息的描述",
                "http://wxtest963.tunnel.qydev.com/static/image/news-big.jpg",
                "http://wxtest963.tunnel.qydev.com/"));
        items.add(new NewsMessageItem("测试标题", "测试图文消息的描述",
                "http://wxtest963.tunnel.qydev.com/static/image/pic-small.jpg",
                "http://wxtest963.tunnel.qydev.com/"));
        String msg = formatReplyMessage(to, from, items);
        return msg;
    }

    @Override
    public String formatReplyMessage(String from, String to, Object obj) throws Exception {
        if (obj == null) {
            throw new Exception("图文消息参数为空");
        }
        List<NewsMessageItem> list = (ArrayList<NewsMessageItem>) obj;
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
        msg = MessageFormat.format(msg, to, from, time, count, buffer.toString());
        return msg;
    }
}
