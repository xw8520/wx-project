package com.models.wx.tag;

import com.models.web.BaseResp;
import com.models.wx.WxBaseResp;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/28.
 */
public class CreateTagResp extends WxBaseResp {
    private Tag tag;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
