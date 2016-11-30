package com.service.api;

import com.models.wx.WxBaseResp;
import com.models.wx.tag.BatchTaggingReq;
import com.models.wx.tag.CreateTagResp;
import com.models.wx.tag.UpdateTagReq;

/**
 * Created by Admin on 2016/5/20.
 */
public interface WxTagService {
    CreateTagResp createTag(String name, int accountid);

    WxBaseResp updateTag(UpdateTagReq req);

    WxBaseResp batchTagging(BatchTaggingReq req);
}
