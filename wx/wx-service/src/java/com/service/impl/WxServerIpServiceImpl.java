package com.service.impl;

import com.dto.wx.TokenDto;
import com.dto.wx.WxServiceIpsDto;
import com.service.AccessTokenService;
import com.service.WxServerIpService;
import com.utils.AcceptTypeEnum;
import com.utils.HttpUtils;
import com.utils.JsonUtils;
import com.wxconfig.WxConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 2016/2/23.
 */
@Service("wxServerIpService")
public class WxServerIpServiceImpl implements WxServerIpService {

    @Resource
    AccessTokenService accessTokenService;

    public List<String> getWxServerIp(int accountId) throws IOException {
        String url = WxConfig.getWxConfig("wx.wxserveripservice");
        TokenDto token = accessTokenService.getAccessToken(accountId);
        url = String.format(url, token.getAccess_token());
        String json = HttpUtils.doGet(url, AcceptTypeEnum.json);
        WxServiceIpsDto ips = JsonUtils.Deserialize(json, WxServiceIpsDto.class);

        return ips != null&&ips.getIp_list()!=null
                ? Arrays.asList(ips.getIp_list())
                : new ArrayList<String>();
    }
}
