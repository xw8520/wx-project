package com.dto.wx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqing on 2016/3/2.
 */
public class GetUserInfoArgs implements Serializable {

    public  GetUserInfoArgs(){
        user_list=new ArrayList<UserInfoArg>();
    }

    public List<UserInfoArg> getUser_list() {
        return user_list;
    }

    public void addUserInfoArg(UserInfoArg arg){
        user_list.add(arg);
    }

    public void setUser_list(List<UserInfoArg> user_list) {
        this.user_list = user_list;
    }

    private List<UserInfoArg> user_list;

    public class UserInfoArg implements Serializable{
        private String openid;
        private String lang;

        public UserInfoArg(){}

        public UserInfoArg(String openid,String lang){
            this.openid=openid;
            this.lang=lang;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }
    }
}

