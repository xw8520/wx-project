package com.data;

import com.domain.wx.WxMedia;

/**
 * Created by Admin on 2016/3/19.
 */
public interface WxMediaMapper {
    void addMedia(WxMedia media);

    WxMedia getMediaByMediaId(String mediaId);

    WxMedia getMediaById(int id);
}
