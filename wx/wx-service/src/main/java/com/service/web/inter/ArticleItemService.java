package com.service.web.inter;

import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.media.AddArticleItemReq;
import com.models.web.media.ArticleItemInfo;
import com.models.web.media.DeleteArticleItemReq;

import java.util.List;

/**
 * Created by admin on 2016/11/26.
 */
public interface ArticleItemService {
    BaseResp addArticleItem(AddArticleItemReq data);

    DataListResp getArticleItemList(int pageSize,int pageIndex,String args);

    BaseResp deleteAddArticleItem(DeleteArticleItemReq data);

    ArticleItemInfo getArticleItem(int id);

}
