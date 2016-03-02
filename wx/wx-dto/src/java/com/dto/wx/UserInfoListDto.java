package com.dto.wx;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangqing on 2016/3/2.
 */
public class UserInfoListDto implements Serializable {
    private List<UserInfoDto> user_info_list;

    public List<UserInfoDto> getUser_info_list() {
        return user_info_list;
    }

    public void setUser_info_list(List<UserInfoDto> user_info_list) {
        this.user_info_list = user_info_list;
    }
}
