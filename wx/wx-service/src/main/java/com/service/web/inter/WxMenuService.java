package com.service.web.inter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.models.web.BaseResp;
import com.models.web.wxmenu.AddWxMenuReq;

import java.io.IOException;

/**
 * Created by wq on 2017/4/8.
 */
public interface WxMenuService {

    BaseResp addMenu(AddWxMenuReq req) throws IOException;
}
