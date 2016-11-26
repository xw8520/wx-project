package com.data;

import com.domain.wx.AccessToken;

/**
 * Created by Admin on 2016/2/22.
 */
public interface AccessTokenMapper {
    /**
     *
     * @param accountid
     * @return
     */
    AccessToken getAccessTokenByAccount(int accountid);

    /**
     *
     * @param token
     * @return
     */
    AccessToken getAccessTokenByToken(String token);

    /**
     *
     * @param token
     */
    void addAccessToken(AccessToken token);
}
