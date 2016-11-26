package com.service.web.impl;

import com.data.UsersMapper;
import com.domain.web.Users;
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
            resp.setInfo("请输入账号密码");
            return resp;
        }
        password = Md5.md5(password);
        Map<String, String> param = new HashMap<>();
        param.put("account", account);
        param.put("password", password);
        Users user = userMapper.getUserInfo(param);
        if (user != null) {
            resp.setSuccess(true);
            resp.setDomain(user.getDomain());
            resp.setName(user.getName());
            resp.setId(user.getId());
            return resp;
        }
        resp.setInfo("账户不存在或者密码错误");
        return resp;
    }
}
