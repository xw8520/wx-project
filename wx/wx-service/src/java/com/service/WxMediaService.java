package com.service;

import com.dto.wx.enums.TmpMediaType;
import com.dto.wx.media.UploadTmpMediaResp;

import java.io.File;


/**
 * Created by Admin on 2016/3/13.
 * 微信素材管理接口
 */
public interface WxMediaService {
    /**
     * 上传临时素材
     *
     * @param path
     * @return
     */
    UploadTmpMediaResp uploadTmpMedia(String path, int accountId,
                                      String title, String remark) throws Exception;

    /**
     * 获取临时素材
     *
     * @param mediaId
     * @param accountId
     * @param localFile
     * @return
     * @throws Exception
     */
    File downLoadTmpMedia(String mediaId, int accountId, String localFile) throws Exception;

    /**
     * 获取素材类型
     *
     * @param fileName
     * @return
     */
    TmpMediaType getMediaType(String fileName);
}
