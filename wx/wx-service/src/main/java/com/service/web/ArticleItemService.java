package com.service.web;

import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.media.AddArticleItemReq;
import com.models.web.media.ArticleItemInfo;

import java.util.List;

/**
 * Created by admin on 2016/11/26.
 */
public interface ArticleItemService {
    BaseResp addArticleItem(AddArticleItemReq data);

    DataListResp getArticleItemList(int pageSize,int pageIndex,String args);

    BaseResp deleteAddArticleItem(List<Integer> data);

    ArticleItemInfo getArticleItem(int id);

}
