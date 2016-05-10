package com.data;

import com.domain.web.Users;

/**
 * Created by TimLin on 2016/5/10.
 */
public interface UsersMapper {
    Users getUser(int id);

    int login(String account,String password);
}
