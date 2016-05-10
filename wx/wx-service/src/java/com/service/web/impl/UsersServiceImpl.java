package com.service.web.impl;

import com.data.UsersMapper;
import com.models.web.LoginResp;
import com.service.web.UsersService;
import com.utils.Md5;
import com.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TimLin on 2016/5/10.
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {

    @Resource
    UsersMapper userMapper;

    @Override
    public LoginResp login(String account, String password) {
        LoginResp resp = new LoginResp();
        if (StringUtils.isNullOrEmpty(account) || StringUtils.isNullOrEmpty(password)) {
            resp.setMsg("请输账号和密码");
            return resp;
        }
        password = Md5.md5(password);
        Map<String, String> map = new HashMap<>();
        map.put("account", account);
        map.put("password", password);
        int exist = userMapper.login(map);
        if (exist > 0) {
            resp.setSuccess(true);
            return resp;
        }
        resp.setMsg("账户不存在或者密码错误");
        return resp;
    }
}
