package com.service.api.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.models.web.BaseResp;
import com.models.wx.WxBaseResp;
import com.models.wx.message.*;
import com.models.wx.token.TokenResp;
import com.service.api.AccessTokenService;
import com.service.api.WxMassMsgService;
import com.service.wxutil.WxUrlUtils;
import com.utils.AcceptTypeEnum;
import com.utils.HttpUtils;
import com.utils.JsonUtils;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by admin on 2016/11/27.
 */
@Service("wxMassMsgService")
public class WxMassMsgServiceImpl implements WxMassMsgService {

    @Resource
    AccessTokenService accessTokenService;

    private String getToken(int accountId) {
        try {
            TokenResp token = accessTokenService.getAccessToken(accountId);
            return token.getAccess_token();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private WxMassMsgResp sendMsgByTagId(String msg, int accountId) {
        String url = WxUrlUtils.getInstance().getSendByTagId();
        url = String.format(url, getToken(accountId));
        String strResp = HttpUtils.doPost(url, msg, AcceptTypeEnum.json);
        WxMassMsgResp resp = new WxMassMsgResp();
        try {
            resp = JsonUtils.Deserialize(strResp, WxMassMsgResp.class);
        } catch (IOException e) {
            e.printStackTrace();
            resp.setErrmsg("系统出错");
        }

        return resp;
    }

    private WxMassMsgResp sendMsgByOpenId(String msg, int accountId) {
        String url = WxUrlUtils.getInstance().getSendMassByOpenId();
        url = String.format(url, getToken(accountId));
        String strResp = HttpUtils.doPost(url, msg, AcceptTypeEnum.json);
        WxMassMsgResp resp = new WxMassMsgResp();
        try {
            resp = JsonUtils.Deserialize(strResp, WxMassMsgResp.class);
        } catch (IOException e) {
            e.printStackTrace();
            resp.setErrmsg("系统出错");
        }

        return resp;
    }

    private WxBaseResp previewMsg(String msg, int accountId) {
        String url = WxUrlUtils.getInstance().getPreview();
        url = String.format(url, getToken(accountId));
        String strResp = HttpUtils.doPost(url, msg, AcceptTypeEnum.json);
        WxBaseResp resp = new WxBaseResp();
        try {
            resp = JsonUtils.Deserialize(strResp, WxBaseResp.class);
        } catch (IOException e) {
            e.printStackTrace();
            resp.setErrmsg("系统出错");
        }
        return resp;
    }

    @Override
    public WxMassMsgResp sendArticleMsgByTag(ArticleMsgReq req) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        ObjectNode filter = node.putObject("filter");
        filter.put("is_to_all", req.isToAll());
        filter.put("tag_id", req.getTagId());
        ObjectNode mpnews = node.putObject("mpnews");
        mpnews.put("media_id", req.getMediaId());
        node.put("msgtype", "mpnews");
        WxMassMsgResp resp = new WxMassMsgResp();
        try {
            String json = mapper.writeValueAsString(node);
            resp = sendMsgByTagId(json, req.getAccountId());
        } catch (IOException e) {
            e.printStackTrace();
            resp.setErrmsg("系统出错");
        }
        return resp;
    }

    @Override
    public WxMassMsgResp sendTextMsgByTag(TextMsgReq req) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        ObjectNode filter = node.putObject("filter");
        filter.put("is_to_all", req.isToAll());
        filter.put("tag_id", req.getTagId());
        ObjectNode text = node.putObject("text");
        text.put("content", req.getContent());
        node.put("msgtype", "text");
        WxMassMsgResp resp = new WxMassMsgResp();
        try {
            String json = mapper.writeValueAsString(node);
            resp = sendMsgByTagId(json, req.getAccountId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            resp.setErrmsg("系统出错");
        }

        return resp;
    }

    @Override
    public WxMassMsgResp sendImageMsgByTag(ImageMsgReq req) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        ObjectNode filter = node.putObject("filter");
        filter.put("is_to_all", req.isToAll());
        filter.put("tag_id", req.getTagId());
        ObjectNode image = node.putObject("image");
        image.put("media_id", req.getMediaId());
        node.put("msgtype", "image");
        WxMassMsgResp resp = new WxMassMsgResp();
        try {
            String json = mapper.writeValueAsString(node);
            resp = sendMsgByTagId(json, req.getAccountId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            resp.setErrmsg("系统出错");
        }
        return resp;
    }

    @Override
    public WxMassMsgResp sendVoiceMsgByTag(VoiceMsgReq req) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        ObjectNode filter = node.putObject("filter");
        filter.put("is_to_all", req.isToAll());
        filter.put("tag_id", req.getTagId());
        ObjectNode voice = node.putObject("voice");
        voice.put("media_id", req.getMediaId());
        node.put("msgtype", "voice");
        WxMassMsgResp resp = new WxMassMsgResp();
        try {
            String json = mapper.writeValueAsString(node);
            resp = sendMsgByTagId(json, req.getAccountId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            resp.setErrmsg("系统出错");
        }
        return resp;
    }

    @Override
    public WxMassMsgResp sendArticleMsgByOpenId(ArticleMsgReq req) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.putPOJO("touser", req.getOpenId());
        ObjectNode mpnews = node.putObject("mpnews");
        mpnews.put("media_id", req.getMediaId());
        node.put("msgtype", "mpnews");
        WxMassMsgResp resp = new WxMassMsgResp();
        try {
            String json = mapper.writeValueAsString(node);
            resp = sendMsgByOpenId(json, req.getAccountId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            resp.setErrmsg("系统出错");
        }
        return resp;
    }

    @Override
    public WxMassMsgResp sendTextMsgByOpenId(TextMsgReq req) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.putPOJO("touser", req.getOpenId());
        ObjectNode mpnews = node.putObject("text");
        mpnews.put("content", req.getContent());
        node.put("msgtype", "text");
        WxMassMsgResp resp = new WxMassMsgResp();
        try {
            String json = mapper.writeValueAsString(node);
            resp = sendMsgByOpenId(json, req.getAccountId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            resp.setErrmsg("系统出错");
        }
        return resp;
    }

    @Override
    public WxMassMsgResp sendImageMsgByOpenId(ImageMsgReq req) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.putPOJO("touser", req.getOpenId());
        ObjectNode mpnews = node.putObject("image");
        mpnews.put("media_id", req.getMediaId());
        node.put("msgtype", "image");
        WxMassMsgResp resp = new WxMassMsgResp();
        try {
            String json = mapper.writeValueAsString(node);
            resp = sendMsgByOpenId(json, req.getAccountId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            resp.setErrmsg("系统出错");
        }
        return resp;
    }

    @Override
    public WxMassMsgResp sendVoiceMsgByOpenId(VoiceMsgReq req) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.putPOJO("touser", req.getOpenId());
        ObjectNode mpnews = node.putObject("voice");
        mpnews.put("media_id", req.getMediaId());
        node.put("msgtype", "voice");
        WxMassMsgResp resp = new WxMassMsgResp();
        try {
            String json = mapper.writeValueAsString(node);
            resp = sendMsgByOpenId(json, req.getAccountId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            resp.setErrmsg("系统出错");
        }
        return resp;
    }

    @Override
    public WxBaseResp previewArticleMsg(PreviewArticleReq req) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("touser", req.getOpenId());
        ObjectNode mpnews = node.putObject("mpnews");
        mpnews.put("media_id", req.getMediaId());
        node.put("msgtype", "mpnews");
        WxBaseResp resp = new WxBaseResp();
        try {
            String msg = mapper.writeValueAsString(node);
            resp = previewMsg(msg, req.getAccountId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    public WxBaseResp previewTextMsg(PreviewTextReq req) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("touser", req.getOpenId());
        ObjectNode text = node.putObject("text");
        text.put("content", req.getConent());
        node.put("msgtype", "text");
        WxBaseResp resp = new WxBaseResp();
        try {
            String msg = mapper.writeValueAsString(node);
            resp = previewMsg(msg, req.getAccountId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    public WxBaseResp previewImageMsg(PreviewImageReq req) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("touser", req.getOpenId());
        ObjectNode image = node.putObject("image");
        image.put("media_id", req.getMediaId());
        node.put("msgtype", "image");
        WxBaseResp resp = new WxBaseResp();
        try {
            String msg = mapper.writeValueAsString(node);
            resp = previewMsg(msg, req.getAccountId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    public WxBaseResp previewVoiceMsg(PreviewVoiceReq req) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("touser", req.getOpenId());
        ObjectNode voice = node.putObject("voice");
        voice.put("media_id", req.getMediaId());
        node.put("msgtype", "voice");
        WxBaseResp resp = new WxBaseResp();
        try {
            String msg = mapper.writeValueAsString(node);
            resp = previewMsg(msg, req.getAccountId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    public SendStatusResp getSendStatus(SendStatusReq req) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("msg_id", req.getMsgId());
        SendStatusResp resp = new SendStatusResp();
        try {
            String json = mapper.writeValueAsString(node);
            String token = getToken(req.getAccountId());
            String url = WxUrlUtils.getInstance().getMassStatus();
            url = String.format(url, token);
            String str = HttpUtils.doPost(url, json, AcceptTypeEnum.json);
            resp = JsonUtils.Deserialize(str, SendStatusResp.class);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setErrmsg("系统出错");
        }
        return resp;
    }

    @Override
    public WxBaseResp deleteMsg(DeleteMsgReq req) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("msg_id", req.getMsgId());
        WxBaseResp resp = new WxBaseResp();
        try {
            String json = mapper.writeValueAsString(node);
            String token = getToken(req.getAccountId());
            String url = WxUrlUtils.getInstance().deleteMass();
            url = String.format(url, token);
            String str = HttpUtils.doPost(url, json, AcceptTypeEnum.json);
            resp = JsonUtils.Deserialize(str, SendStatusResp.class);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setErrmsg("系统出错");
        }
        return resp;
    }
}
