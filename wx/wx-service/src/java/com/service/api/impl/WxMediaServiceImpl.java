package com.service.api.impl;

import com.data.WxMediaMapper;
import com.domain.wx.WxMedia;
import com.models.wx.token.TokenResp;
import com.enums.WxMediaType;
import com.models.wx.media.ArticleItem;
import com.service.api.AccessTokenService;
import com.service.api.WxMediaService;
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
import org.springframework.web.servlet.resource.AppCacheManifestTransformer;

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
     * @param path
     * @return
     */
    @Override
    public Map<String, Object> addTmpMedia(String path, int accountId, int domain,
                                           String title, String remark) throws Exception {
        String url = WxUrlUtils.getInstance().getTmpMediaUpload();
        WxMediaType mediaType = getMediaType(path);
        if (mediaType == WxMediaType.unknow) {
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
        Map<String, Object> map = JsonUtils.Deserialize(str, HashMap.class);
        if (map.containsKey("errcode")) {
            map.put("success", false);
            map.put("info", map.get("errmsg"));
            return map;
        }
        Calendar expire = Calendar.getInstance();
        expire.add(Calendar.DATE, 3);
        //添加临时素材记录
        WxMedia media = new WxMedia();
        media.setRemark(remark);
        media.setCreatetime(new Date());
        media.setExpiretime(expire.getTime());
        media.setAccountid(accountId);
        media.setPermanent(false);
        media.setMediatype(mediaType.getValue());
        media.setTitle(title);
        media.setDomain(domain);
        media.setMediaid(map.get("media_id") != null ? map.get("media_id").toString() : "");
        wxMediaMapper.insert(media);

        map.put("success", true);
        return map;
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
    public String uploadNews(List<ArticleItem> list, int accountId) throws Exception {
        String url = WxUrlUtils.getInstance().getUploadNews();

        TokenResp token = accessTokenService.getAccessToken(accountId);
        if (token == null || !StringUtils.isNullOrEmpty(token.getErrmsg())) {
            throw new Exception("获取token 失败");
        }
        url = String.format(url, token.getAccess_token());
        Hashtable hash = new Hashtable();
        hash.put("articles", list);

        String param = JsonUtils.Serialize(hash);
        System.out.println(param);
        String json = HttpUtils.doPost(url, param, AcceptTypeEnum.json);
        System.out.println(json);

        Hashtable r = JsonUtils.Deserialize(json, Hashtable.class);
        if (r != null && r.containsKey("media_id")) {
            logger.debug(r.get("media_id").toString());
            return r.get("media_id").toString();
        }

        return "";
    }

    @Override
    public Map<String, Object> addMaterial(String path, int accountId, int domain,
                                           String title, String remark) throws Exception {
        String url = WxUrlUtils.getInstance().getAddmaterial();
        WxMediaType mediaType = getMediaType(path);
        if (mediaType == WxMediaType.unknow) {
            throw new Exception("文件格式有误");
        }
        TokenResp token = accessTokenService.getAccessToken(accountId);
        if (token == null || !StringUtils.isNullOrEmpty(token.getErrmsg())) {
            throw new Exception("获取token 失败");
        }
        url = String.format(url, token.getAccess_token(), mediaType.toString());
        logger.debug(url);
        List<NameValuePair> param = new ArrayList<>();
        //在上传视频素材时需要POST另一个表单，id为description，
        // 包含素材的描述信息，内容格式为JSON
        if (mediaType == WxMediaType.video) {
            String json = MessageFormat.format("{\"title\":{0},\"introduction\":{1}\"}", title, remark);
            NameValuePair desc = new BasicNameValuePair("description", json);
            param.add(desc);
        }

        String str = HttpUtils.doPost(url, path, param);
        if (StringUtils.isNullOrEmpty(str)) {
            throw new Exception("上传永久素材失败");
        }
        Map<String, Object> map = new HashMap<>();
        Hashtable hash = JsonUtils.Deserialize(str, Hashtable.class);
        if (hash != null && hash.containsKey("media_id")) {
            String mediaId = hash.get("media_id").toString();
            String mediaUrl = hash.get("url").toString();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 100);
            //添加临时素材记录
            WxMedia media = new WxMedia();
            media.setRemark(remark);
            media.setCreatetime(new Date());
            media.setExpiretime(calendar.getTime());
            media.setAccountid(accountId);
            media.setPermanent(true);
            media.setMediatype(mediaType.getValue());
            media.setTitle(title);
            media.setDomain(domain);
            media.setMediaid(mediaId);
            media.setUrl(mediaUrl);
            wxMediaMapper.insert(media);
            map.put("success", true);
        } else {
            logger.debug(hash.get("errcode") + "");
            logger.debug(hash.get("errmsg") + "");
            map.put("success", false);
            map.put("info", "上传素材失败");
        }

        return map;
    }
}
