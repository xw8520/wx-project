package com.service.web;

import com.models.web.media.AddArticleReq;
import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.media.ArticleInfo;

import java.util.List;

/**
 * Created by admin on 2016/11/26.
 */
public interface ArticleService {

    BaseResp addArticle(AddArticleReq data);

    ArticleInfo getArticle(Integer id);

    DataListResp getArticleList(int pageSize, int pageIndex, int domain, String args);

    BaseResp deleteArticle(List<Integer> data, boolean deleteWx);

    BaseResp sendArticleToWx(Integer id);
}
