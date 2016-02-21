package com.impl;

import com.service.WxBindService;

/**
 * Created by Admin on 2016/2/21.
 */
public class WxBindServiceImpl implements WxBindService {
    public String Reply(String signature, String timestamp, String nonce,
                        String echostr) {
        System.out.println("echostr:"+echostr);
        System.out.println("signature:"+signature);
        System.out.println("timestamp:"+timestamp);
        System.out.println("nonce:"+nonce);
        return echostr;
    }
}
