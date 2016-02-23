package com.service.impl;

import com.service.WxBindService;
import com.utils.SHA1;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Admin on 2016/2/21.
 */
public class WxBindServiceImpl implements WxBindService {
    public String Reply(String signature, String timestamp, String nonce,
                        String echostr) throws NoSuchAlgorithmException {
        System.out.println("signature:"+signature);
        String tmp= SHA1.gen("2016",timestamp,nonce);
        System.out.println("tmp:"+tmp);
        if(signature.equals(tmp)){
            return echostr;
        }
        return "";
    }
}
