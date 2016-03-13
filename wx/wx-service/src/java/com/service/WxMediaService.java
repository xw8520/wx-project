package com.service;

import com.dto.wx.enums.TmpMediaType;
import com.dto.wx.media.UploadTmpMediaRespDto;

/**
 * Created by Admin on 2016/3/13.
 * 微信素材管理接口
 */
public interface WxMediaService {
    /**
     * 上传临时素材
     * @param mediaType
     * @param path
     * @return
     */
    UploadTmpMediaRespDto uploadTmpMedia(TmpMediaType mediaType,String path);
}
