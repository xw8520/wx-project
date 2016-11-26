package com.domain.wx;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Admin on 2016/2/22.
 */
public class AccessToken implements Serializable {
    public AccessToken(){}
    public AccessToken(int accountid,String token,Date expiredtime){
        this.accountid=accountid;
        this.token=token;
        this.expiredtime=expiredtime;
        createtime=new Date();
    }
    private int id;
    private int accountid;
    private String token;
    private Date createtime;
    private Date expiredtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getExpiredtime() {
        return expiredtime;
    }

    public void setExpiredtime(Date expiredtime) {
        this.expiredtime = expiredtime;
    }
}
