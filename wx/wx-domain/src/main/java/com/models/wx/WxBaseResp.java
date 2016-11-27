package com.models.wx;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/26.
 */
public class WxBaseResp implements Serializable {
    private String errmsg;
    private int errcode;
    private String msg_id;

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
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
}
