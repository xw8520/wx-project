package com.models.wx.media;

import com.models.wx.WxBaseResp;

/**
 * Created by admin on 2016/11/26.
 */
public class AddTmpMediaResp extends WxBaseResp {
    private String media_id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
}
