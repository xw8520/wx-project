package com.service.impl;

import com.data.WxMediaMapper;
import com.domain.wx.WxMedia;
import com.dto.wx.TokenResp;
import com.dto.wx.enums.TmpMediaType;
import com.dto.wx.media.UploadTmpMediaResp;
import com.service.AccessTokenService;
import com.service.WxMediaService;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.utils.HttpUtils;
import com.utils.JsonUtils;
import com.utils.StringUtils;
import com.wxconfig.WxConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.regex.Pattern;

/**
 * Created by Admin on 2016/3/13.
 * 微信素材管理
 */
@Service("wxMediaService")
public class WxMediaServiceImpl implements WxMediaService {
    @Resource
    AccessTokenService accessTokenService;

    @Resource
    WxMediaMapper wxMediaMapper;

    /**
     * 上传临时素材
     *
     * @param path
     * @return
     */
    @Override
    public UploadTmpMediaResp uploadTmpMedia(String path, int accountId,
                                             String title, String remark) throws Exception {
        String url = WxConfig.getInstance().getTmpMediaUpload();
        TmpMediaType mediaType = getMediaType(path);
        if (mediaType == TmpMediaType.unknow) {
            throw new Exception("文件格式有误");
        }
        TokenResp token = accessTokenService.getAccessToken(accountId);
        if (token == null || !StringUtils.isNullOrEmpty(token.getErrmsg())) {
            throw new Exception("获取token 失败");
        }
        url = String.format(url, token.getAccess_token(), mediaType.toString());

        String str = HttpUtils.doPost(url, path, new VirtualFlow.ArrayLinkedList<>());
        if (StringUtils.isNullOrEmpty(str)) {
            throw new Exception("上传素材失败");
        }
        System.out.println(str);
        UploadTmpMediaResp resp = JsonUtils.Deserialize(str, UploadTmpMediaResp.class);
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        //添加临时素材记录
        WxMedia media = new WxMedia(title, remark, accountId, mediaType.getValue(),
                fileName, resp.getMedia_id(), false);
        wxMediaMapper.addMedia(media);
        return resp;
    }

    /**
     * 获取临时素材
     *
     * @param mediaId
     * @param accountId
     * @param fileName
     * @return
     * @throws Exception
     */
    @Override
    public File downLoadTmpMedia(String mediaId, int accountId,
                                 String fileName)
            throws Exception {
        String url = WxConfig.getInstance().getTmpmedia();
        TokenResp token = accessTokenService.getAccessToken(accountId);
        if (token == null || !StringUtils.isNullOrEmpty(token.getErrmsg())) {
            throw new Exception("获取token 失败");
        }
        url = String.format(url, token.getAccess_token(), mediaId);
        InputStream input = HttpUtils.doGet(url);
        if (input == null) {
            throw new Exception("下载文件失败");
        }
        byte[] buffer = new byte[1024];
        File file = new File(fileName);
        FileOutputStream out = new FileOutputStream(file);
        int size = 0;
        while ((size = input.read(buffer)) != -1) {
            out.write(buffer, 0, size);
        }
        input.close();
        out.close();
        return file;
    }

    /**
     * 获取素材类型
     *
     * @param fileName
     * @return
     */
    public TmpMediaType getMediaType(String fileName) {
        Pattern image = Pattern.compile("^\\S+(\\.(?i)(bmp|png|jpeg|jpg|gif))$");
        if (image.matcher(fileName).matches()) {
            if (fileName.contains("thumb")) {
                return TmpMediaType.thumb;
            }
            return TmpMediaType.image;
        }
        Pattern voice = Pattern.compile("^\\S+(\\.(?i)(mp3|wma|wav|amr))$");
        if (voice.matcher(fileName).matches()) {
            return TmpMediaType.voice;
        }
        Pattern video = Pattern.compile("^\\S+(\\.(?i)(MP4))$");
        if (video.matcher(fileName).matches()) {
            return TmpMediaType.video;
        }
        return TmpMediaType.unknow;
    }
}
