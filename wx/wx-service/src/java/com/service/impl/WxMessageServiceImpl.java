package com.service.impl;

import com.data.QrCodeMapper;
import com.domain.wx.QrCode;
import com.dto.wx.QrCodeTicketRespDto;
import com.dto.wx.TokenDto;
import com.dto.wx.WxServiceIpsDto;
import com.service.AccessTokenService;
import com.service.WxMessageService;
import com.utils.AcceptTypeEnum;
import com.utils.HttpUtils;
import com.utils.JsonUtils;
import com.utils.SHA1;
import com.wxconfig.WxConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by Admin on 2016/2/23.
 */
@Service("wxMessageService")
public class WxMessageServiceImpl implements WxMessageService {

    @Resource
    AccessTokenService accessTokenService;
    @Resource
    QrCodeMapper qrCodeMapper;

    public String reply(String body) {
        System.out.println(body);
        return body;
    }

    @Override
    public String reply(String signature, String timestamp, String nonce, String echostr)
            throws NoSuchAlgorithmException {
        System.out.println("signature:" + signature);
        String tmp = SHA1.gen("2016", timestamp, nonce);
        System.out.println("tmp:" + tmp);
        if (signature.equals(tmp)) {
            return echostr;
        }
        return "";
    }

    @Override
    public List<String> getWxServerIp(int accountId) throws IOException {
        String url = WxConfig.getWxConfig("wx.wxserveripservice");
        TokenDto token = accessTokenService.getAccessToken(accountId);
        url = String.format(url, token.getAccess_token());
        String json = HttpUtils.doGet(url, AcceptTypeEnum.json);
        WxServiceIpsDto ips = JsonUtils.Deserialize(json, WxServiceIpsDto.class);

        return ips != null && ips.getIp_list() != null
                ? Arrays.asList(ips.getIp_list())
                : new ArrayList<String>();
    }

    @Override
    public String getQrCode(String param, int expireTime, int accountId) throws Exception {
        if (StringUtils.isEmpty(param)) return "";
        QrCode code = qrCodeMapper.getCodeByParam(param);
        String qrcodeUrl = WxConfig.getWxConfig("wx.getqrcode");
        if (code != null && code.getExpiredtime().after(new Date())) {
            return String.format(qrcodeUrl, code.getTicket());
        }
        String ticketUrl = WxConfig.getWxConfig("wx.createticket");
        ticketUrl = String.format(ticketUrl, accessTokenService.getAccessToken(accountId));
        int p = 0;
        String ticket = "";
        try {
            Integer.valueOf(param);
        } catch (Exception ex) {
            if (expireTime == 0) {
                throw new Exception("参数错误");
            }
            p = -1;
        }
        if (p == -1) {
            ticket = getTicket(p, expireTime, ticketUrl);
        } else {
            ticket = getTicket(param, ticketUrl);
        }
        if (StringUtils.isEmpty(ticket)) {
            throw new Exception("获取ticket失败");
        }
        return String.format(qrcodeUrl, ticket);
    }


    private String getTicket(int param, int expireTime, String url) throws Exception {
        String str;
        //永久二维码
        if (expireTime == 0) {
            str = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + param + "}}}";
        } else {
            str = "{\"expire_seconds\": " + expireTime + ", \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + param + "}}}";
        }
        String json = HttpUtils.doPost(url, str, AcceptTypeEnum.json);
        QrCodeTicketRespDto result = JsonUtils.Deserialize(json, QrCodeTicketRespDto.class);
        if (result == null) return "";
        if (!StringUtils.isEmpty(result.getErrcode())) {
            throw new Exception(result.getErrmsg());
        }
        QrCode code = qrCodeMapper.getCodeByParam(String.valueOf(param));
        if (code != null && expireTime != 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, result.getExpire_seconds());
            code.setExpiredtime(calendar.getTime());
            qrCodeMapper.updateQrCode(code);
        } else {
            code = new QrCode();
            code.setParam(String.valueOf(param));
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, result.getExpire_seconds());
            code.setExpiredtime(calendar.getTime());
            code.setTicket(result.getTicket());
            code.setCreatetime(new Date());
            qrCodeMapper.addQrCode(code);
        }

        return result.getTicket();
    }

    private String getTicket(String param, String url) throws Exception {
        String str = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"" + param + "\"}}}";
        String json = HttpUtils.doPost(url, str, AcceptTypeEnum.json);
        QrCodeTicketRespDto result = JsonUtils.Deserialize(json, QrCodeTicketRespDto.class);
        if (result == null) return "";
        if (!StringUtils.isEmpty(result.getErrcode())) {
            throw new Exception(result.getErrmsg());
        }

        QrCode code = new QrCode();
        code.setParam(String.valueOf(param));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1000);
        code.setExpiredtime(calendar.getTime());
        code.setTicket(result.getTicket());
        code.setCreatetime(new Date());
        qrCodeMapper.addQrCode(code);

        return result.getTicket();
    }
}
