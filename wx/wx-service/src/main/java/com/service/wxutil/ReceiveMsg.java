package com.service.wxutil;

import com.utils.XmlParseUtils;
import org.dom4j.Document;

import java.io.Serializable;

/**
 * Created by Admin on 2016/3/6.
 * 公众号消息报文
 */
public class ReceiveMsg implements Serializable {
    private Document doc;

    public ReceiveMsg(Document doc) {
        this.doc = doc;
    }

    /**
     * 获取信息接收者Openid
     *
     * @return
     */
    public String getToUserName() {
        String str = XmlParseUtils.getDocElementTextByPath(doc, "xml/ToUserName");
        return str;
    }

    /**
     * 消息发送者Openid
     *
     * @return
     */
    public String getFromUserName() {
        return XmlParseUtils.getDocElementTextByPath(doc, "xml/FromUserName");
    }

    /**
     * 回复时间
     *
     * @return
     */
    public Long getCreateTime() {
        String str = XmlParseUtils.getDocElementTextByPath(doc, "xml/CreateTime");
        return Long.parseLong(str);
    }

    /**
     * 消息类型
     *
     * @return
     */
    public String getMsgType() {
        return XmlParseUtils.getDocElementTextByPath(doc, "xml/MsgType");
    }

    /**
     * 消息内容
     *
     * @return
     */
    public String getContent() {
        return XmlParseUtils.getDocElementTextByPath(doc, "xml/Content");
    }

    /**
     * 消息id
     *
     * @return
     */
    public int getMsgId() {
        String str = XmlParseUtils.getDocElementTextByPath(doc, "xml/MsgId");
        return Integer.parseInt(str);
    }

    /**
     * 获取事件类型
     *
     * @return
     */
    public String getEvent() {
        String str = XmlParseUtils.getDocElementTextByPath(doc, "xml/Event");
        return str;
    }

    /**
     * 获取事件 KEY值
     * 带参二维码 qrscene_为前缀，后面为二维码的参数值
     * 已关注时的事件推送事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     *
     * @return
     */
    public String getEventKey() {
        return XmlParseUtils.getDocElementTextByPath(doc, "xml/EventKey");
    }

    /**
     * 二维码的ticket，可用来换取二维码图片
     * @return
     */
    public String getTicket(){
        return XmlParseUtils.getDocElementTextByPath(doc,"xml/Ticket");
    }
}
