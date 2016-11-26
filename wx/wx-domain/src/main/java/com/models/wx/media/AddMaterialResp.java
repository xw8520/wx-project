package com.models.wx.media;

import com.models.wx.WxBaseResp;

/**
 * Created by admin on 2016/11/26.
 */
public class AddMaterialResp extends WxBaseResp {
    private String media_id;
    private String url;

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
