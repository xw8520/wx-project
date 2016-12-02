package com.service.api.inter;

import com.models.wx.WxBaseResp;
import com.models.wx.tag.*;

/**
 * Created by Admin on 2016/5/20.
 */
public interface WxTagService {
    CreateTagResp createTag(String name, int accountid);

    WxBaseResp updateTag(UpdateTagReq req);

    WxBaseResp deleteTag(WxDeleteTagReq req);

    TagListResp getTagList(Integer accountId);

    WxBaseResp batchTagging(BatchTaggingReq req);
}
