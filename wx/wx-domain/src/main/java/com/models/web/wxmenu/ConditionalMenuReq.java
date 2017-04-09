package com.models.web.wxmenu;

/**
 * Created by wq on 2017/4/8.
 */
public class ConditionalMenuReq extends WxMenuReq {
    private MatchRule matchrule;

    public MatchRule getMatchrule() {
        return matchrule;
    }

    public void setMatchrule(MatchRule matchrule) {
        this.matchrule = matchrule;
    }
}
