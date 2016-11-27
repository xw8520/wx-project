package com.service.api.impl;

import com.models.wx.message.CustomNewsMsgItem;
import com.service.wxutil.WxMsgUtils;
import com.utils.StringUtils;
import com.models.wx.token.TokenResp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.service.api.AccessTokenService;
import com.service.api.WxCustomMsgService;
import com.utils.AcceptTypeEnum;
import com.utils.HttpUtils;
import com.service.wxutil.WxUrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Admin on 2016/3/19.
 * 客服接口
 */
@Service("wxSendMsgService")
public class WxCustomMsgServiceImpl implements WxCustomMsgService {
    @Resource
    AccessTokenService accessTokenService;

    static Logger log = LoggerFactory.getLogger(WxCustomMsgServiceImpl.class);

    /**
     * 发送文本消息
     *
     * @param to
     * @param msg
     * @return
     */
    @Override
    public String sendCustomTextMsg(String to, String msg, int accountid) {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode node = mapper.createObjectNode();
        node.put("touser", to);
        node.put("msgtype", "text");
        ObjectNode content = node.putObject("text");
        content.put("content", msg);
        String str = "";
        try {
            str = mapper.writeValueAsString(node);
            String url = WxUrlUtils.getInstance().getSendTextMsg();
            TokenResp token = accessTokenService.getAccessToken(accountid);
            url = String.format(url, token.getAccess_token());
            String result = HttpUtils.doPost(url, str, AcceptTypeEnum.json);
            log.debug(result);

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }

    /**
     * 微信发送图片消息
     *
     * @param to
     * @param mediaId
     * @param accountid
     * @return
     */
    @Override
    public String sendCustomImgMsg(String to, String mediaId, int accountid) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("touser", to);
        node.put("msgtype", "image");
        ObjectNode content = node.putObject("image");
        content.put("media_id", mediaId);
        String str;
        try {
            str = mapper.writeValueAsString(node);
            String url = WxUrlUtils.getInstance().getSendTextMsg();
            TokenResp token = accessTokenService.getAccessToken(accountid);
            url = String.format(url, token.getAccess_token());
            String result = HttpUtils.doPost(url, str, AcceptTypeEnum.json);
            log.debug(result);

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 发送图文消息
     * @param to
     * @param list
     * @param accountid
     * @return
     */
    @Override
    public String sendCustomNewsMsg(String to, List<CustomNewsMsgItem> list, int accountid) {
        String msg = WxMsgUtils.getCustomNewsMsg(to, list);
        if (StringUtils.isNullOrEmpty(msg)) {
            return "get msg error";
        }
        String url = WxUrlUtils.getInstance().getSendTextMsg();
        try {
            TokenResp token = accessTokenService.getAccessToken(accountid);
            url = String.format(url, token.getAccess_token());
            String result = HttpUtils.doPost(url, msg, AcceptTypeEnum.json);
            log.debug(result);
            return result;
        } catch (Exception ex) {
            log.error("sendCustomNewsMsg", ex);
        }
        return "send error";
    }
}
