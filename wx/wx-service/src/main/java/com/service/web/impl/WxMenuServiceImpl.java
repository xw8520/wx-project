package com.service.web.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.models.web.BaseResp;
import com.models.web.wxmenu.*;
import com.models.wx.WxBaseResp;
import com.service.api.inter.AccessTokenService;
import com.service.web.inter.WxMenuService;
import com.service.wxutil.WxUrlUtils;
import com.utils.AcceptTypeEnum;
import com.utils.HttpUtils;
import com.utils.JsonUtils;
import com.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wq on 2017/4/8.
 */
@Service("wxMenuService")
public class WxMenuServiceImpl implements WxMenuService {
    @Resource
    AccessTokenService accessTokenService;

    @Override
    public BaseResp addMenu(WxMenuReq req) throws IOException {
        BaseResp resp = new BaseResp();
        if (req.getButton() == null || req.getButton().size() == 0) {
            resp.setInfo("参数错误");
            return resp;
        }
        String token = accessTokenService.getAccessToken2(req.getAccountId());
        String url = String.format(WxUrlUtils.getInstance().getCreateWxMenu(), token);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        List<Object> menuList = getMenuList(req.getButton());
        node.putPOJO("button", menuList);
        String json = mapper.writeValueAsString(node);
        System.out.println(json);

        String respStr = HttpUtils.doPost(url, json, AcceptTypeEnum.json);
        System.out.println(respStr);
        WxBaseResp wxBaseResp = JsonUtils.Deserialize(respStr, WxBaseResp.class);
        if (wxBaseResp.getErrcode() == 0) {
            resp.setSuccess(true);
        } else {
            resp.setInfo("修改菜单失败");
        }
        return resp;
    }

    @Override
    public BaseResp getWxMenu(Integer accountId) {
        BaseResp resp = new BaseResp();
        if (accountId == null) {
            resp.setInfo("参数错误");
            return resp;
        }
        String token = accessTokenService.getAccessToken2(accountId);
        String url = String.format(WxUrlUtils.getInstance().getMenu(), token);
        String respStr = HttpUtils.doGet(url, AcceptTypeEnum.json);
        try {
            WxMenuResp wxMenuResp = JsonUtils.Deserialize(respStr, WxMenuResp.class);
            resp.setData(wxMenuResp);
            resp.setSuccess(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resp;
    }

    @Override
    public BaseResp addConditionalMenu(ConditionalMenuReq req) {
        BaseResp resp = new BaseResp();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        List<Object> menuList = getMenuList(req.getButton());
        node.putPOJO("button", menuList);
        try {
            node.putPOJO("matchrule", req.getMatchrule());
            String json = mapper.writeValueAsString(node);
            String url = accessTokenService.getAddConditionalUrl(req.getAccountId());
            String respStr = HttpUtils.doPost(url, json, AcceptTypeEnum.json);
            AddConditionalMenuResp wxBaseResp = JsonUtils.Deserialize(respStr, AddConditionalMenuResp.class);
            if (wxBaseResp.getErrcode() == 0) {
                resp.setSuccess(true);
                resp.setData(wxBaseResp.getMenuid());
            } else {
                resp.setInfo(wxBaseResp.getErrmsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setInfo("程序出错");
        }

        return resp;
    }

    private List<Object> getMenuList(List<ComplexMenu> menus) {
        List<Object> menuList = new ArrayList<>();

        for (ComplexMenu menu : menus) {
            //普通菜单
            if (!StringUtils.isNullOrEmpty(menu.getUrl())) {
                BaseMenu baseMenu = new BaseMenu();
                baseMenu.setName(menu.getName());
                baseMenu.setUrl(menu.getUrl());
                baseMenu.setType("view");
                menuList.add(baseMenu);
            } else if (menu.getSub_button() != null && menu.getSub_button().size() > 0) {
                menuList.add(menu);
            }
        }
        return menuList;
    }
}
