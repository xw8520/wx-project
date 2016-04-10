package com.service.api;

import com.enums.WxMediaType;
import com.models.wx.media.ArticleItem;
import com.models.wx.media.UploadTmpMediaResp;

import java.io.File;
import java.util.List;


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
    WxMediaType getMediaType(String fileName);

    /**
     * 图文消息图片上传
     *本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下
     * @param path
     * @param accountId
     * @return
     */
    String uploadImage(String path, int accountId) throws Exception;

    /**
     * 上传图文消息
     *
     * @param list
     * @param accountId
     * @return
     */
    String uploadNews(List<ArticleItem> list, int accountId) throws Exception;

    /**
     * 上传永久素材
     *
     * @param path
     * @param accountId
     * @return
     */
    String addMaterial(String path, int accountId) throws Exception;
}
