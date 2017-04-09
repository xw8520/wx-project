package com.models.web.wxmenu;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wq on 2017/4/8.
 */
public class WxMenuReq implements Serializable {
    private int accountId;
    private List<ComplexMenu> button;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public List<ComplexMenu> getButton() {
        return button;
    }

    public void setButton(List<ComplexMenu> button) {
        this.button = button;
    }
}
