package com.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
}
