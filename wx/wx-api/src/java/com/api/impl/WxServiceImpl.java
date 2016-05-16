package com.api.impl;

import com.api.WxService;
import com.models.wx.user.QrCodeReq;
import com.service.api.WxMessageService;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Admin on 2016/2/23.
 */
public class WxServiceImpl implements WxService {

    @Resource
    WxMessageService wxMsgService;


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
}
