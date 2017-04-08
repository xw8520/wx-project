package com.service.web.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.models.web.BaseResp;
import com.models.web.MenuInfo;
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
    public BaseResp addMenu(AddWxMenuReq req) throws IOException {
        BaseResp resp = new BaseResp();
        if (req.getButton() == null || req.getButton().size() == 0) {
            resp.setInfo("参数错误");
            return resp;
        }
        String token = accessTokenService.getAccessToken2(req.getAccountId());
        String url = String.format(WxUrlUtils.getInstance().getWxMenu(), token);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        List<Object> menuList = new ArrayList<>();
        node.putPOJO("button", menuList);
        for (ComplexMenu menu : req.getButton()) {
            //普通菜单
            if (!StringUtils.isNullOrEmpty(menu.getUrl())) {
                menuList.add(BaseMenu2MenuInfo(menu));
            } else if (menu.getSubMenu() != null && menu.getSubMenu().size() > 0) {
                ComplexMenuInfo complexMenuInfo = new ComplexMenuInfo();
                complexMenuInfo.setName(menu.getName());
                List<WxMenuInfo> newMenus = new ArrayList<>();
                for (BaseMenu baseMenu : menu.getSubMenu()) {
                    newMenus.add(BaseMenu2MenuInfo(baseMenu));
                }
                complexMenuInfo.setSub_button(newMenus);
                menuList.add(complexMenuInfo);
            }
        }
        String json = mapper.writeValueAsString(node);
        System.out.println(json);

        String respStr = HttpUtils.doPost(url, json, AcceptTypeEnum.json);
        System.out.println(respStr);
        WxBaseResp wxBaseResp = JsonUtils.Deserialize(respStr, WxBaseResp.class);
        if(wxBaseResp.getErrcode()==0){
            resp.setSuccess(true);
        }else{
            resp.setInfo("修改菜单失败");
        }
        return resp;
    }


    private WxMenuInfo BaseMenu2MenuInfo(BaseMenu baseMenu) {
        WxMenuInfo newMenu = new WxMenuInfo();
        newMenu.setName(baseMenu.getName());
        newMenu.setType("view");
        newMenu.setUrl(baseMenu.getUrl());
        return newMenu;
    }
}
