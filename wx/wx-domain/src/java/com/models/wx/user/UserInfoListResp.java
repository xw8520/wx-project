package com.models.wx.user;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangqing on 2016/3/2.
 */
public class UserInfoListResp implements Serializable {
    private List<UserInfoResp> user_info_list;

    public List<UserInfoResp> getUser_info_list() {
        return user_info_list;
    }

    public void setUser_info_list(List<UserInfoResp> user_info_list) {
        this.user_info_list = user_info_list;
    }
}
