package com.api.impl;

import com.api.WxService;
import com.models.wx.message.CustomTextMsg;
import com.models.wx.user.QrCodeReq;
import com.models.wx.user.UserInfoResp;
import com.service.api.WxMessageService;
import com.service.api.WxSendMsgService;

import javax.annotation.Resource;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Configurable;
import javax.ws.rs.core.Context;
import java.security.NoSuchAlgorithmException;
import java.security.cert.PKIXRevocationChecker;

/**
 * Created by Admin on 2016/2/23.
 */
public class WxServiceImpl implements WxService {

    @Resource
    WxMessageService wxMsgService;

    @Resource
    WxSendMsgService wxSendMsgService;

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
}
