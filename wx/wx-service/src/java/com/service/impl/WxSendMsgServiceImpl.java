package com.service.impl;

import com.dto.wx.NewsMessageItem;
import com.dto.wx.ReceiveMsg;
import com.dto.wx.TokenResp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.service.AccessTokenService;
import com.service.WxSendMsgService;
import com.utils.AcceptTypeEnum;
import com.utils.HttpUtils;
import com.wxconfig.WxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Admin on 2016/3/19.
 * 格式化微信消息
 */
@Service("wxSendMsgService")
public class WxSendMsgServiceImpl implements WxSendMsgService {
    @Resource
    AccessTokenService accessTokenService;

    static Logger log = LoggerFactory.getLogger(WxSendMsgServiceImpl.class);

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

    /**
     * 发送文本消息
     *
     * @param to
     * @param msg
     * @return
     */
    @Override
    public String sendCustomTextMsg(String to, String msg, int accountid) {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode node = mapper.createObjectNode();
        node.put("touser", to);
        node.put("msgtype", "text");
        ObjectNode content = node.putObject("text");
        content.put("content", msg);
        String str = "";
        try {
            str = mapper.writeValueAsString(node);
            String url = WxConfig.getInstance().getSendTextMsg();
            TokenResp token = accessTokenService.getAccessToken(accountid);
            url = String.format(url, token.getAccess_token());
            String result = HttpUtils.doPost(url, str, AcceptTypeEnum.json);
            log.debug(result);

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }

    /**
     * 微信发送图片消息
     *
     * @param to
     * @param mediaId
     * @param accountid
     * @return
     */
    @Override
    public String sendCustomImgMsg(String to, String mediaId, int accountid) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("touser", to);
        node.put("msgtype", "image");
        ObjectNode content = node.putObject("image");
        content.put("media_id", mediaId);
        String str;
        try {
            str = mapper.writeValueAsString(node);
            String url = WxConfig.getInstance().getSendTextMsg();
            TokenResp token = accessTokenService.getAccessToken(accountid);
            url = String.format(url, token.getAccess_token());
            String result = HttpUtils.doPost(url, str, AcceptTypeEnum.json);
            log.debug(result);

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
