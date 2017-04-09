package com.service.web.inter;

import com.models.web.BaseResp;
import com.models.web.wxmenu.ConditionalMenuReq;
import com.models.web.wxmenu.WxMenuReq;

import java.io.IOException;

/**
 * Created by wq on 2017/4/8.
 */
public interface WxMenuService {

    BaseResp addMenu(WxMenuReq req) throws IOException;

    BaseResp getWxMenu(Integer accountId);

    BaseResp addConditionalMenu(ConditionalMenuReq req);
}
