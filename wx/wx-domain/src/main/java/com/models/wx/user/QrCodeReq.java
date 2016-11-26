package com.models.wx.user;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by TimLin on 2016/5/16.
 */
@XmlRootElement(name = "data")
public class QrCodeReq implements Serializable {
    @XmlAttribute
    private   String param;
    @XmlAttribute
    private   int expireTime;
    @XmlAttribute
    private int accountId;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
