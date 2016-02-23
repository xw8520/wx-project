package com.service.impl;

import com.service.WxMsgService;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/2/23.
 */
@Service("wxMsgService")
public class WxMsgServiceImpl implements WxMsgService {

    public String Reply(String body) {
        System.out.println(body);
        return "";
    }
}
