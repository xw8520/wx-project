package com.service.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.models.wx.token.TokenResp;
import com.service.api.AccessTokenService;
import com.service.api.WxTagService;
import com.service.wxutil.WxUrlUtils;
import com.utils.AcceptTypeEnum;
import com.utils.HttpUtils;
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
    public String createTag(String name, int accountid) throws Exception {
        String url = WxUrlUtils.getInstance().getCreateTag();
        TokenResp token = accessTokenService.getAccessToken(accountid);
        url = String.format(url, token.getAccess_token());
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        ObjectNode content = node.putObject("tag");
        content.put("name", name);
        String json = mapper.writeValueAsString(node);
        return HttpUtils.doPost(url, json, AcceptTypeEnum.json);
    }
}
