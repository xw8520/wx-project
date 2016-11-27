package com.service.web.impl;

import com.data.ArticleItemMapper;
import com.data.ArticlesMapper;
import com.domain.wx.ArticleItem;
import com.domain.wx.ArticleItemExample;
import com.domain.wx.Articles;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.media.AddArticleItemReq;
import com.models.web.media.ArticleItemInfo;
import com.models.web.media.DeleteArticleItemReq;
import com.service.web.ArticleItemService;
import com.utils.JsonUtils;
import com.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by admin on 2016/11/26.
 */
@Service("articleItemService")
public class ArticleItemServiceImpl implements ArticleItemService {
    @Resource
    ArticleItemMapper articleItemMapper;
    @Resource
    ArticlesMapper articlesMapper;

    @Override
    public BaseResp addArticleItem(AddArticleItemReq data) {
        BaseResp resp = new BaseResp();
        ArticleItem item = new ArticleItem();
        item.setTitle(data.getTitle());
        item.setAuthor(data.getAuthor());
        item.setContent(data.getContent());
        item.setContentSourceUrl(data.getContentUrl());
        item.setDigest(data.getDigest());
        item.setShowCoverPic(data.getShowCover());
        item.setArticlesid(data.getArticleId());
        item.setThumbMediaId(data.getMediaId());
        item.setId(data.getId());
        if (data.getId() > 0) {
            boolean s = articleItemMapper.updateByPrimaryKey(item) > 0;
            resp.setSuccess(s);
            return resp;
        }
        boolean s = articleItemMapper.insert(item) > 0;
        resp.setSuccess(s);
        return resp;
    }

    @Override
    public DataListResp getArticleItemList(int pageSize, int pageIndex, String args) {
        DataListResp resp = new DataListResp();
        try {
            Map<String, String> map = (HashMap<String, String>) JsonUtils.Deserialize(args, HashMap.class);
            ArticleItemExample exp = new ArticleItemExample();
            ArticleItemExample.Criteria c = exp.createCriteria();
            if (map.containsKey("aid")) {
                c.andArticlesidEqualTo(Integer.valueOf(map.get("aid")));
            }
            PageHelper.startPage(pageIndex, pageSize);
            List<ArticleItem> tmp = articleItemMapper.selectByExample(exp);
            PageInfo<ArticleItem> pageInfo = new PageInfo<>(tmp);
            resp.setTotal(pageInfo.getTotal());
            List<ArticleItemInfo> list = tmp.stream()
                    .map(f -> articleItemMap(f))
                    .collect(Collectors.toList());
            resp.setList(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    private ArticleItemInfo articleItemMap(ArticleItem item) {
        return new ArticleItemInfo(
                item.getId(), item.getThumbMediaId(),
                item.getAuthor(), item.getTitle(),
                item.getContentSourceUrl(), item.getContent(),
                item.getDigest(), item.getShowCoverPic(),
                item.getArticlesid()
        );
    }

    @Override
    public BaseResp deleteAddArticleItem(DeleteArticleItemReq data) {
        BaseResp resp = new BaseResp();
        Articles articles = articlesMapper.selectByPrimaryKey(data.getAid());
        if (articles != null && !StringUtils.isNullOrEmpty(articles.getMediaid())) {
            resp.setInfo("已经发送不能删除");
            return resp;
        }
        ArticleItemExample exp = new ArticleItemExample();
        ArticleItemExample.Criteria c = exp.createCriteria();
        c.andIdIn(data.getData());

        resp.setSuccess(articleItemMapper.deleteByExample(exp) > 0);
        return resp;
    }

    @Override
    public ArticleItemInfo getArticleItem(int id) {
        ArticleItem data = articleItemMapper.selectByPrimaryKey(id);
        return articleItemMap(data);
    }
}
