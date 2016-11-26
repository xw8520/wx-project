package com.service.api;

import com.models.wx.message.CustomNewsMsgItem;

import java.util.List;

/**
 * Created by Admin on 2016/3/11.
 * 文本消息
 */
public interface WxCustomMsgService {

    /**
     * 客服接口 -发送文本消息
     * @param to
     * @param msg
     * @return
     */
    String sendCustomTextMsg(String to, String msg, int accountid);

    /**
     * 微信发送图片
     * @param to
     * @param mediaId
     * @param accountid
     * @return
     */
    String sendCustomImgMsg(String to,String mediaId, int accountid);

    /**
     *  客服接口发送图文消息
     * @param to
     * @param accountid
     * @return
     */
    String sendCustomNewsMsg(String to, List<CustomNewsMsgItem> list, int accountid);
}
