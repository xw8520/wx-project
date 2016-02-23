package com.api.impl;

import com.api.WxService;
import com.service.WxMsgService;

import javax.annotation.Resource;

/**
 * Created by Admin on 2016/2/23.
 */
public class WxServiceImpl implements WxService {

    @Resource
    WxMsgService wxMsgService;


    public String Reply(String body) {
        return wxMsgService.Reply(body);
    }
}
