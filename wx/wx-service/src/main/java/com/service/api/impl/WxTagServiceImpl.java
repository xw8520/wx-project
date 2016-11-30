package com.service.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.models.wx.WxBaseResp;
import com.models.wx.tag.BatchTaggingReq;
import com.models.wx.tag.CreateTagResp;
import com.models.wx.tag.UpdateTagReq;
import com.models.wx.token.TokenResp;
import com.service.api.AccessTokenService;
import com.service.api.WxTagService;
import com.service.wxutil.WxUrlUtils;
import com.utils.AcceptTypeEnum;
import com.utils.HttpUtils;
import com.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Admin on 2016/5/20.
 * 标签相关
 */
@Service("wxTagService")
public class WxTagServiceImpl implements WxTagService {
    Logger logger = LoggerFactory.getLogger(WxTagServiceImpl.class);
    @Resource
    AccessTokenService accessTokenService;

    @Override
    public CreateTagResp createTag(String name, int accountid) {
        CreateTagResp resp = new CreateTagResp();
        try {
            String url = WxUrlUtils.getInstance().getCreateTag();
            TokenResp token = accessTokenService.getAccessToken(accountid);
            url = String.format(url, token.getAccess_token());
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();
            ObjectNode content = node.putObject("tag");
            content.put("name", name);
            String json = mapper.writeValueAsString(node);
            String str = HttpUtils.doPost(url, json, AcceptTypeEnum.json);
            logger.info("createTag:" + str);
            resp = JsonUtils.Deserialize(str, CreateTagResp.class);
        } catch (Exception ex) {
            logger.error("createTag", ex);
            resp.setErrmsg("系统出错");
        }
        return resp;
    }

    @Override
    public WxBaseResp updateTag(UpdateTagReq req) {
        WxBaseResp resp = new WxBaseResp();
        try {
            String url = WxUrlUtils.getInstance().updateTag();
            url = String.format(url, accessTokenService.getAccessToken2(req.getAccountId()));
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();
            ObjectNode tag = node.putObject("tag");
            tag.put("id", req.getTagId());
            tag.put("name", req.getName());
            String param = mapper.writeValueAsString(node);
            String str = HttpUtils.doPost(url, param, AcceptTypeEnum.json);
            resp = mapper.readValue(str, WxBaseResp.class);
        } catch (Exception ex) {
            logger.error("updateTag", ex);
            resp.setErrmsg("系统出错");
            resp.setErrcode(500);
        }

        return resp;
    }

    @Override
    public WxBaseResp batchTagging(BatchTaggingReq req) {
        WxBaseResp resp = new WxBaseResp();
        try {
            String url = WxUrlUtils.getInstance().getBatchtagging();
            String token = accessTokenService.getAccessToken2(req.getAccountId());
            url = String.format(url, token);
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();
            node.putPOJO("openid_list", req.getOpenId());
            node.put("tagid", req.getTagId());
            String param = JsonUtils.Serialize(node);
            String str = HttpUtils.doPost(url, param, AcceptTypeEnum.json);
            logger.info("batchTagging:" + str);
            resp = JsonUtils.Deserialize(str, WxBaseResp.class);
        } catch (Exception ex) {
            logger.error(WxTagServiceImpl.class.getName(), ex);
            resp.setErrmsg("系统出错");
        }
        return resp;
    }
}
