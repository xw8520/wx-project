package com.dto.wx;

import com.utils.XmlParseUtils;
import org.dom4j.Document;

import java.io.Serializable;

/**
 * Created by Admin on 2016/3/6.
 * 公众号消息报文
 */
public class ReceiveDto implements Serializable {
    private Document doc;
    public ReceiveDto(Document doc){
        this.doc=doc;
    }

    /**
     * 获取信息接收者Openid
     * @return
     */
    public String getToUserName(){
        String str= XmlParseUtils.getDocElementTextByPath(doc,"xml/ToUserName");
        return str;
    }

    /**
     * 消息发送者Openid
     * @return
     */
    public String getFromUserName(){
        return XmlParseUtils.getDocElementTextByPath(doc,"xml/FromUserName");
    }

    /**
     * 回复时间
     * @return
     */
    public Long getCreateTime(){
        String str=XmlParseUtils.getDocElementTextByPath(doc,"xml/CreateTime");
        return Long.parseLong(str);
    }

    /**
     *消息类型
     * @return
     */
    public String getMsgType(){
        return XmlParseUtils.getDocElementTextByPath(doc,"xml/MsgType");
    }

    /**
     * 消息内容
     * @return
     */
    public String getContent(){
        return XmlParseUtils.getDocElementTextByPath(doc,"xml/Content");
    }

    /**
     * 消息id
     * @return
     */
    public int getMsgId(){
        String str=XmlParseUtils.getDocElementTextByPath(doc,"xml/MsgId");
        return Integer.parseInt(str);
    }
}
