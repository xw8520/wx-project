package com.data;

import com.domain.web.Users;

import java.util.Map;

/**
 * Created by TimLin on 2016/5/10.
 */
public interface UsersMapper {
    Users getUser(int id);

    int login(Map<String,String> map);
}
