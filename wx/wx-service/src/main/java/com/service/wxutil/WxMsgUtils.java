package com.service.wxutil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.models.wx.message.CustomNewsMsgItem;
import com.models.wx.message.NewsMsgItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Admin on 2016/5/19.
 */
public class WxMsgUtils {
    private static Logger logger = LoggerFactory.getLogger(WxMsgUtils.class);

    /**
     * 被动回复 文本消息
     *
     * @param receive
     * @param msg
     * @return
     */
    public static String getTextMsg(ReceiveMsg receive, String msg) {
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

    /**
     * 被动回复 图片消息
     *
     * @param receive
     * @param mediaId
     * @return
     */
    public static String getImageMsg(ReceiveMsg receive, String mediaId) {
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

    /**
     * 被动回复 语音消息
     *
     * @param receive
     * @param mediaId
     * @return
     */
    public static String getVoiceMsg(ReceiveMsg receive, String mediaId) {
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

    /**
     * 被动回复 图文消息
     *
     * @param receive
     * @param list
     * @return
     */
    public static String getNewsMsg(ReceiveMsg receive, List<NewsMsgItem> list) {
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
        for (NewsMsgItem s : list) {
            buffer.append(String.format(item, s.getTitle(), s.getDesc(), s.getPicUrl(), s.getUrl()));
        }
        Long time = Calendar.getInstance().getTimeInMillis();
        msg = MessageFormat.format(msg, to, from, String.valueOf(time), count, buffer.toString());
        return msg;
    }

    /**
     * 被动回复 视频消息
     *
     * @param receive
     * @param mediaId
     * @param title
     * @param desc
     * @return
     */
    public static String getVideoMsg(ReceiveMsg receive, String mediaId, String title, String desc) {
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

    /**
     * 被动回复 视频消息
     *
     * @param receive
     * @param mediaId
     * @param title
     * @param desc
     * @param misucUrl
     * @param hqUrl
     * @return
     */
    public static String getMusicMsg(ReceiveMsg receive, String mediaId, String title,
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

    /**
     * 获取客服接口图文消息
     *
     * @param to
     * @param list
     * @return
     */
    public static String getCustomNewsMsg(String to, List<CustomNewsMsgItem> list) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("touser", to);
        node.put("msgtype", "news");
        ObjectNode content = node.putObject("news");
        content.putPOJO("articles", list);
        try {
            String str = mapper.writeValueAsString(node);
            return str;
        } catch (Exception ex) {
            logger.error("获取客服接口图文消息失败", ex);
        }
        return "";
    }
}
