package com.service.web.impl;

import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.media.AddArticleItemReq;
import com.models.web.media.ArticleItemInfo;
import com.service.web.ArticleItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2016/11/26.
 */
@Service("articleItemService")
public class ArticleItemServiceImpl implements ArticleItemService {
    @Override
    public BaseResp addArticleItem(AddArticleItemReq data) {
        return null;
    }

    @Override
    public DataListResp getArticleItemList(int pageSize, int pageIndex, String args) {
        return null;
    }

    @Override
    public BaseResp deleteAddArticleItem(List<Integer> data) {
        return null;
    }

    @Override
    public ArticleItemInfo getArticleItem(int id) {
        return null;
    }
}
