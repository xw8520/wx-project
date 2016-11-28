package com.api.impl;

import com.api.WxService;
import com.models.wx.message.CustomNewsMsg;
import com.models.wx.message.CustomTextMsg;
import com.models.wx.message.TextMsgReq;
import com.models.wx.message.WxMassMsgResp;
import com.models.wx.tag.CreateTagReq;
import com.models.wx.user.QrCodeReq;
import com.models.wx.user.UserInfoResp;
import com.service.api.WxMassMsgService;
import com.service.api.WxMsgService;
import com.service.api.WxUserService;
import com.service.api.WxTagService;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Admin on 2016/2/23.
 */
public class WxServiceImpl implements WxService {

    @Resource
    WxUserService wxMsgService;
    @Resource
    WxMsgService wxSendMsgService;
    @Resource
    WxMassMsgService wxMassMsgService;

    @Resource
    WxTagService wxTagService;

    public String reply(String body) {
        return wxMsgService.reply(body);
    }

    @Override
    public String reply(String signature, String timestamp, String nonce, String echostr) throws NoSuchAlgorithmException {
        return wxMsgService.reply(signature, timestamp, nonce, echostr);
    }

    @Override
    public String getQrCode(QrCodeReq data) throws Exception {
        if (data == null) return "";
        return wxMsgService.getQrCode(data.getParam(), data.getExpireTime(), data.getAccountId());
    }

    @Override
    public UserInfoResp getUserInfo(int accountid, String openid) throws Exception {
        return wxMsgService.getUserInfo(accountid, openid);
    }

    @Override
    public String sendCustomTextMsg(CustomTextMsg data) {
        if (data == null) return "";
        return wxSendMsgService.sendCustomTextMsg(data.getTo(), data.getMsg(), data.getAccountid());
    }

    @Override
    public String sendCustomNewsMsg(CustomNewsMsg data) {
        if (data == null) return "";
        return wxSendMsgService.sendCustomNewsMsg(data.getTo(), data.getList(), data.getAccountid());
    }

    @Override
    public WxMassMsgResp sendTextMsgByOpenId(TextMsgReq req) {
        return wxMassMsgService.sendTextMsgByOpenId(req);
    }
}
