package com.service.web;

import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.tag.AddTagReq;
import com.models.web.tag.DeleteTagReq;
import com.models.web.tag.WxTagInfo;

/**
 * Created by admin on 2016/11/30.
 */
public interface TagService {
    BaseResp addTag(AddTagReq req);

    WxTagInfo getTag(int tagId);

    DataListResp getTagList(int pageSize, int pageIndex, int domain, String args);

    BaseResp deleteTag(DeleteTagReq req);
}
