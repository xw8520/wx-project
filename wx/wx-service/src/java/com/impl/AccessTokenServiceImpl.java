package com.impl;

import com.cache.EhCacheManager;
import com.cache.EhKeys;
import com.data.AccessTokenMapper;
import com.data.AccountMapper;
import com.domain.wx.AccessToken;
import com.domain.wx.Account;
import com.dto.token.TokenDto;
import com.service.AccessTokenService;
import com.sun.beans.editors.LongEditor;
import com.utils.AcceptTypeEnum;
import com.utils.HttpUtils;
import com.utils.JsonUtils;
import com.wxconfig.WxConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Admin on 2016/2/21.
 */
@Service("accessTokenService")
public class AccessTokenServiceImpl implements AccessTokenService {

    @Resource
    AccountMapper accountMapper;
    @Resource
    AccessTokenMapper accessTokenMapper;
    @Resource
    EhCacheManager ehCacheManager;

    /**
     * @param accountid
     * @return
     * @throws IOException
     */
    public TokenDto getAccessToken(int accountid) throws IOException {
        TokenDto dto;
        String key = String.format(EhKeys.accessTokenKey, accountid);
        AccessToken token = ehCacheManager.get(key, AccessToken.class);
        if (token != null && token.getExpiredtime().after(new Date())) {
            dto = new TokenDto();
            dto.setAccess_token(token.getToken());
            System.out.println("get token by cache");
            return dto;
        }
        token = accessTokenMapper.getAccessTokenByAccount(accountid);
        if (token != null && token.getExpiredtime().after(new Date())) {
            dto = new TokenDto();
            dto.setAccess_token(token.getToken());
            Long seconds = token.getExpiredtime().getTime() - new Date().getTime();
            ehCacheManager.put(key, token, (int) (seconds/1000));
            return dto;
        }
        Account account = accountMapper.getAccountById(accountid);
        String url = WxConfig.getWxConfig("wx.accesstoken");
        if (!StringUtils.isEmpty(url)) {
            url = String.format(url, account.getAppid(), account.getSecret());
            System.out.println("url:" + url);
            String json = HttpUtils.doGet(url, AcceptTypeEnum.json);
            dto = JsonUtils.Deserialize(json, TokenDto.class);
            if (StringUtils.isEmpty(dto.getErrcode())) {
                token = getAccessTokenByDto(dto, accountid);
                ehCacheManager.put(key, token, dto.getExpires_in());
                accessTokenMapper.addAccessToken(token);
                return dto;
            }
        }
        return new TokenDto();
    }

    /**
     * @param dto
     * @return
     */
    private AccessToken getAccessTokenByDto(TokenDto dto, int accountId) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, dto.getExpires_in());
        AccessToken token = new AccessToken(accountId, dto.getAccess_token(),
                calendar.getTime());
        return token;
    }
}
