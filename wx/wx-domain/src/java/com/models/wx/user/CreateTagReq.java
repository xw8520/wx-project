package com.models.wx.user;


import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Admin on 2016/5/20.
 */
@XmlRootElement()
public class CreateTagReq implements Serializable {
    private int accountid;
    private String name;

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
