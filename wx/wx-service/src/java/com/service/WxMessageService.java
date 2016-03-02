package com.service;

import com.dto.wx.UserInfoDto;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/2/23.
 */
public interface WxMessageService {
    /**
     *
     * @param body
     * @return
     */
    String reply(String body);

    /**
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     * @throws NoSuchAlgorithmException
     */
    String reply(String signature, String timestamp, String nonce, String echostr) throws NoSuchAlgorithmException;

    /**
     *
     * @param accountId
     * @return
     */
    List<String> getWxServerIp(int accountId) throws IOException;

    /**
     *
     * @param param
     * @param expireTime
     * @return
     */
    String getQrCode(String param,int expireTime,int accountId) throws Exception;

    /**
     * 获取jsspi的通行证
     * @param accountId
     * @return
     */
    String getJsapiticket(int accountId) throws Exception;

    /**
     * 获取jsapi签名包
     * @param accountid
     * @param url
     * @return
     */
    Map<String, String> getJsapiSignPackage(int accountid,String url) throws Exception;

    /**
     * 获取用户信息
     * @param accountid
     * @param openid
     * @return
     */
    UserInfoDto getUserInfo(int accountid,String openid) throws Exception;

    /**
     * 批量获取用户信息
     * @param accountid
     * @param openids
     * @return
     */
    List<UserInfoDto> getUserInfoBatch(int accountid,List<String> openids) throws Exception;
}
