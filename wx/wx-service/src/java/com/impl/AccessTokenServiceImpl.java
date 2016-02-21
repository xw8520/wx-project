package com.impl;

import com.data.AccountMapper;
import com.domain.wx.Account;
import com.dto.token.AccessToken;
import com.service.AccessTokenService;
import com.utils.AcceptTypeEnum;
import com.utils.HttpUtils;
import com.utils.JsonUtils;
import com.wxconfig.WxConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Admin on 2016/2/21.
 */
@Service("accessTokenService")
public class AccessTokenServiceImpl implements AccessTokenService {

    @Resource
    AccountMapper accountMapper;

    public AccessToken getAccessToken(int id) throws IOException {
        Account account = accountMapper.getAccountById(id);
        System.out.println(account.getName());

        String url = WxConfig.getWxConfig("wx.accesstoken");
        if (!StringUtils.isEmpty(url)) {

            url = String.format(url, account.getAppid(), account.getSecret());
            System.out.println("url:" + url);
            String json = HttpUtils.doGet(url, AcceptTypeEnum.json);
            AccessToken token = JsonUtils.Deserialize(json, AccessToken.class);
            return token;
        }
        return new AccessToken();
    }
}
