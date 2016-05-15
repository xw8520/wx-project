package com.service.web;

import com.models.web.LoginResp;

import java.util.Map;


/**
 * Created by TimLin on 2016/5/10.
 */
public interface UsersService {

    Map<String,Object> login(String account, String password);
}
