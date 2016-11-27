package com.models.wx.message;

import com.models.wx.WxBaseResp;

/**
 * Created by admin on 2016/11/27.
 */
public class WxMassMsgResp extends WxBaseResp {
    private String msg_id;
    private String msg_data_id;

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_data_id() {
        return msg_data_id;
    }

    public void setMsg_data_id(String msg_data_id) {
        this.msg_data_id = msg_data_id;
    }
}
