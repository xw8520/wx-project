package com.service;

import com.dto.wx.enums.TmpMediaType;
import com.dto.wx.media.UploadTmpMediaResp;

import java.io.IOException;

/**
 * Created by Admin on 2016/3/13.
 * 微信素材管理接口
 */
public interface WxMediaService {
    /**
     * 上传临时素材
     * @param path
     * @return
     */
    UploadTmpMediaResp uploadTmpMedia(String path) throws Exception;
}
