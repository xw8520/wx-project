package com.dto.wx;

import java.io.Serializable;

/**
 * Created by Admin on 2016/2/28.
 */
public class JsapiticketResp implements Serializable {
    private int errcode;
    private String errmsg;
    private String ticket;

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    private int expires_in;
}
