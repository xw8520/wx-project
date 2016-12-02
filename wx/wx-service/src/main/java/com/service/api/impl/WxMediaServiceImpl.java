package com.service.api.impl;

import com.data.WxMediaMapper;
import com.domain.wx.WxMedia;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.models.wx.media.*;
import com.models.wx.token.TokenResp;
import com.enums.WxMediaType;
import com.service.api.inter.AccessTokenService;
import com.service.api.inter.WxMediaService;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.utils.AcceptTypeEnum;
import com.utils.HttpUtils;
import com.utils.JsonUtils;
import com.utils.StringUtils;
import com.service.wxutil.WxUrlUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Admin on 2016/3/13.
 * 微信素材管理
 */
@Service("wxMediaService")
public class WxMediaServiceImpl implements WxMediaService {
    Logger logger = LoggerFactory.getLogger(WxMediaServiceImpl.class);
    @Resource
    AccessTokenService accessTokenService;

    @Resource
    WxMediaMapper wxMediaMapper;

    /**
     * 上传临时素材
     *
     * @return
     */
    @Override
    public AddTmpMediaResp addTmpMedia(AddMaterialReq req) throws Exception {
        String url = WxUrlUtils.getInstance().getTmpMediaUpload();
        WxMediaType mediaType = getMediaType(req.getPath());
        if (mediaType == WxMediaType.unknow) {
            throw new Exception("文件格式有误");
        }
        TokenResp token = accessTokenService.getAccessToken(req.getAccountId());
        if (token == null || !StringUtils.isNullOrEmpty(token.getErrmsg())) {
            throw new Exception("获取token 失败");
        }
        url = String.format(url, token.getAccess_token(),
                WxMediaType.unknow.getTypeName(req.getType()));
        logger.info("addTmpMedia:" + url);
        String str = HttpUtils.doPost(url, req.getPath(), new VirtualFlow.ArrayLinkedList<>());
        if (StringUtils.isNullOrEmpty(str)) {
            throw new Exception("上传素材失败");
        }
        System.out.println(str);
        AddTmpMediaResp addTmpMediaResp = new AddTmpMediaResp();
        try {
            addTmpMediaResp = JsonUtils.Deserialize(str, AddTmpMediaResp.class);
            if (addTmpMediaResp.getErrcode() != 0) {
                return addTmpMediaResp;
            }
            Calendar expire = Calendar.getInstance();
            expire.add(Calendar.DATE, 3);
            //添加临时素材记录
            WxMedia media = new WxMedia();
            media.setRemark(req.getRemark());
            media.setCreatetime(new Date());
            media.setExpiretime(expire.getTime());
            media.setAccountid(req.getAccountId());
            media.setPermanent(false);
            media.setMediatype(mediaType.getValue());
            media.setTitle(req.getTitle());
            media.setDomain(req.getDomain());
            media.setMediaid(addTmpMediaResp.getMedia_id());
            wxMediaMapper.insert(media);
            return addTmpMediaResp;
        } catch (Exception ex) {
            logger.error("addTmpMedia", ex);
            addTmpMediaResp.setErrmsg("系统出错");
        }
        return addTmpMediaResp;
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
                                 String fileName) throws Exception {
        WxMediaType mediaType = getMediaType(fileName);
        String url = "";
        if (mediaType == WxMediaType.video) {
            url = WxUrlUtils.getInstance().getTmpmedia();
        } else {
            url = WxUrlUtils.getInstance().getTmpVedio();
        }

        TokenResp token = accessTokenService.getAccessToken(accountId);
        if (token == null || !StringUtils.isNullOrEmpty(token.getErrmsg())) {
            throw new Exception("获取token 失败");
        }
        url = String.format(url, token.getAccess_token(), mediaId);
        File file = HttpUtils.doGet(url);
        if (file == null) {
            throw new Exception("获取文件失败");
        }
        return file;
    }

    /**
     * 获取素材类型
     *
     * @param fileName
     * @return
     */
    public WxMediaType getMediaType(String fileName) {
        Pattern image = Pattern.compile("^\\S+(\\.(?i)(bmp|png|jpeg|jpg|gif))$");
        if (image.matcher(fileName).matches()) {
            if (fileName.contains("thumb")) {
                return WxMediaType.thumb;
            }
            return WxMediaType.image;
        }
        Pattern voice = Pattern.compile("^\\S+(\\.(?i)(mp3|wma|wav|amr))$");
        if (voice.matcher(fileName).matches()) {
            return WxMediaType.voice;
        }
        Pattern video = Pattern.compile("^\\S+(\\.(?i)(MP4))$");
        if (video.matcher(fileName).matches()) {
            return WxMediaType.video;
        }
        return WxMediaType.unknow;
    }

    @Override
    public String uploadImage(String path, int accountId) throws Exception {
        String url = WxUrlUtils.getInstance().getUploadimg();

        TokenResp token = accessTokenService.getAccessToken(accountId);
        if (token == null || !StringUtils.isNullOrEmpty(token.getErrmsg())) {
            throw new Exception("获取token 失败");
        }
        url = String.format(url, token.getAccess_token());
        String json = HttpUtils.doPost(url, path, new ArrayList<NameValuePair>());
        Hashtable hash = JsonUtils.Deserialize(json, Hashtable.class);
        if (hash != null) {
            if (hash.containsKey("url")) {
                return hash.get("url").toString();
            }
        }
        return "";
    }

    @Override
    public UploadArticleResp uploadArticle(List<WxArticleItem> list, int accountId) throws Exception {
        String url = WxUrlUtils.getInstance().getUploadNews();
        TokenResp token = accessTokenService.getAccessToken(accountId);
        if (token == null || !StringUtils.isNullOrEmpty(token.getErrmsg())) {
            throw new Exception("获取token 失败");
        }
        url = String.format(url, token.getAccess_token());
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.putPOJO("articles", list);
        String param = mapper.writeValueAsString(node);
        String json = HttpUtils.doPost(url, param, AcceptTypeEnum.json);
        UploadArticleResp resp = JsonUtils.Deserialize(json, UploadArticleResp.class);
        return resp;
    }

    @Override
    public AddMaterialResp addMaterial(AddMaterialReq req) throws Exception {
        String url = WxUrlUtils.getInstance().getAddmaterial();
        TokenResp token = accessTokenService.getAccessToken(req.getAccountId());
        if (token == null || !StringUtils.isNullOrEmpty(token.getErrmsg())) {
            throw new Exception("获取token 失败");
        }
        url = String.format(url, token.getAccess_token(),
                WxMediaType.unknow.getTypeName(req.getType()));
        logger.info(url);
        List<NameValuePair> param = new ArrayList<>();
        //在上传视频素材时需要POST另一个表单，id为description，
        // 包含素材的描述信息，内容格式为JSON
        if (req.getType() == WxMediaType.video.getValue()) {
            String json = MessageFormat.format("{\"title\":{0},\"introduction\":{1}\"}",
                    req.getTitle(), req.getRemark());
            NameValuePair desc = new BasicNameValuePair("description", json);
            param.add(desc);
        }

        String str = HttpUtils.doPost(url, req.getPath(), param);
        if (StringUtils.isNullOrEmpty(str)) {
            throw new Exception("上传永久素材失败");
        }
        logger.info("str:" + str);
        AddMaterialResp hash = JsonUtils.Deserialize(str, AddMaterialResp.class);
        if (hash != null && hash.getErrcode() == 0) {
            String mediaId = hash.getMedia_id();
            String mediaUrl = hash.getUrl();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 100);
            //添加临时素材记录
            WxMedia media = new WxMedia();
            media.setRemark(req.getRemark());
            media.setCreatetime(new Date());
            media.setExpiretime(calendar.getTime());
            media.setAccountid(req.getAccountId());
            media.setPermanent(true);
            media.setMediatype((byte) req.getType());
            media.setTitle(req.getTitle());
            media.setDomain(req.getDomain());
            media.setMediaid(mediaId);
            media.setUrl(mediaUrl);
            wxMediaMapper.insert(media);
            return hash;
        }

        return hash;
    }

    @Override
    public GetMaterialResp getMeterail(GetMaterialReq req) {
        String url = WxUrlUtils.getInstance().getMaterial();
        try {
            String token = accessTokenService.getAccessToken(req.getAccountId()).getAccess_token();

            url = String.format(url, token);
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();
            node.put("media_id", req.getMediaId());
            String param = mapper.writeValueAsString(node);
            String str = HttpUtils.doPost(url, param, AcceptTypeEnum.json);
            logger.info("getMeterail:" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new GetMaterialResp();
    }

}
