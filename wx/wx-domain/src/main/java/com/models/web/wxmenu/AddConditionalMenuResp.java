package com.models.web.wxmenu;

import com.models.wx.WxBaseResp;

/**
 * Created by wq on 2017/4/8.
 */
public class AddConditionalMenuResp extends WxBaseResp {
    private int menuid;

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }
}
