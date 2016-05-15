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
    public Map<String, Object> login(String account, String password) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNullOrEmpty(account) || StringUtils.isNullOrEmpty(password)) {
            map.put("msg", "请输账号和密码");
            map.put("success", false);
            return map;
        }
        password = Md5.md5(password);
        Map<String, String> param = new HashMap<>();
        param.put("account", account);
        param.put("password", password);
        Users user = userMapper.getUserInfo(param);
        if (user != null) {
            map.put("success", true);
            map.put("domain", user.getDomain());
            map.put("name", user.getName());
            map.put("id", user.getId());
            return map;
        }
        map.put("success", false);
        map.put("msg","账户不存在或者密码错误");
        return map;
    }
}
