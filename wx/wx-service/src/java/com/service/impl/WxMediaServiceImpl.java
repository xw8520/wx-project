package com.service.impl;

import com.dto.wx.TokenResp;
import com.dto.wx.enums.TmpMediaType;
import com.dto.wx.media.UploadTmpMediaResp;
import com.service.AccessTokenService;
import com.service.WxMediaService;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.utils.HttpUtils;
import com.wxconfig.WxConfig;
import org.apache.http.NameValuePair;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Admin on 2016/3/13.
 * 微信素材管理
 */
@Service("wxMediaService")
public class WxMediaServiceImpl implements WxMediaService {
    @Resource
    AccessTokenService accessTokenService;

    /**
     * 上传临时素材
     *
     * @param mediaType
     * @param path
     * @return
     */
    @Override
    public UploadTmpMediaResp uploadTmpMedia(TmpMediaType mediaType, String path) throws Exception {
        UploadTmpMediaResp resp = new UploadTmpMediaResp();
        String url = WxConfig.getInstance().getTmpMediaUpload();
        TokenResp token = accessTokenService.getAccessToken(1);
        if (token == null || !StringUtils.isEmpty(token.getErrmsg())) {
            throw new Exception("获取token 失败");
        }
        url = String.format(url, token.getAccess_token(), mediaType.toString());
        System.out.println(url);
        String str = HttpUtils.doPost(url, path, new VirtualFlow.ArrayLinkedList<NameValuePair>());
        System.out.println(str);
        return resp;
    }
}
