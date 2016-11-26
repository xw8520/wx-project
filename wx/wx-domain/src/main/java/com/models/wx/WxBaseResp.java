package com.models.wx;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/26.
 */
public class WxBaseResp implements Serializable {
    private String errmsg;
    private int errcode;

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
}
