package com.service.web;

import com.models.web.LoginResp;


/**
 * Created by TimLin on 2016/5/10.
 */
public interface UsersService {

    LoginResp login(String account, String password);
}
