package com.models.wx.tag;

import com.models.wx.WxBaseResp;

import java.util.List;

/**
 * Created by admin on 2016/12/1.
 */
public class TagListResp extends WxBaseResp {
    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
