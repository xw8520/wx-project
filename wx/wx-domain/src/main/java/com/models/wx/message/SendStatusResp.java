package com.models.wx.message;

import com.models.wx.WxBaseResp;


/**
 * Created by admin on 2016/11/27.
 */
public class SendStatusResp extends WxBaseResp {
    private String msg_status;

    public String getMsg_status() {
        return msg_status;
    }

    public void setMsg_status(String msg_status) {
        this.msg_status = msg_status;
    }
}
