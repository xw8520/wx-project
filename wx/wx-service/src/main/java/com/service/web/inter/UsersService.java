package com.service.web.inter;

import com.models.web.LoginResp;

import java.util.Map;


/**
 * Created by TimLin on 2016/5/10.
 */
public interface UsersService {

    LoginResp login(String account, String password);
}
