package com.service.web.impl;

import com.data.AccountMapper;
import com.data.ArticlesMapper;
import com.domain.wx.Articles;
import com.domain.wx.ArticlesExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.models.web.AccountInfo;
import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.media.AddArticleReq;
import com.models.web.media.ArticleInfo;
import com.service.web.AccountService;
import com.service.web.ArticleService;
import com.utils.JsonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by admin on 2016/11/26.
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticlesMapper articlesMapper;
    @Resource
    AccountService accountService;

    @Override
    public BaseResp addArticle(AddArticleReq data) {
        BaseResp resp = new BaseResp();

        Articles articles = new Articles();
        articles.setRemark(data.getRemark());
        articles.setId(data.getId());
        articles.setTitle(data.getTitle());
        if (articles.getId() > 0) {
            ArticlesExample exp = new ArticlesExample();
            ArticlesExample.Criteria criteria = exp.createCriteria();
            criteria.andIdEqualTo(data.getId());
            boolean success = articlesMapper.updateByExampleSelective(articles, exp) > 0;
            resp.setSuccess(success);
            return resp;
        }
        articles.setType(data.getType());
        articles.setDomain(data.getDomain());
        articles.setAccountid(data.getAccountid());
        articles.setCreatetime(new Date());
        boolean success = articlesMapper.insert(articles) > 0;
        resp.setSuccess(success);
        return resp;
    }

    @Override
    public ArticleInfo getArticle(Integer id) {
        Articles articles = articlesMapper.selectByPrimaryKey(id);
        return ArticleInfoMap(articles);
    }

    @Override
    public DataListResp getArticleList(int pageSize, int pageIndex, int domain, String args) {
        DataListResp resp = new DataListResp();
        ArticlesExample exp = new ArticlesExample();
        ArticlesExample.Criteria c = exp.createCriteria();
        c.andDomainEqualTo(domain);
        try {
            Map<String, String> map = (HashMap<String, String>) JsonUtils.Deserialize(args, HashMap.class);
            if (map.containsKey("accountid")) {
                c.andAccountidEqualTo(Integer.parseInt(map.get("accountid")));
            }
            if (map.containsKey("id")) {
                c.andIdEqualTo(Integer.parseInt(map.get("id")));
            }
            if (map.containsKey("title")) {
                c.andTitleLike("%" + map.get("title") + "%");
            }
            PageHelper.startPage(pageIndex, pageSize);
            List<Articles> tmp = articlesMapper.selectByExample(exp);
            PageInfo pageInfo = new PageInfo(tmp);
            resp.setTotal(pageInfo.getTotal());
            List<ArticleInfo> list = tmp.stream()
                    .map(q -> ArticleInfoMap(q))
                    .collect(Collectors.toList());
            resp.setList(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resp;
    }

    private ArticleInfo ArticleInfoMap(Articles item) {
        ArticleInfo newItem = new ArticleInfo(
                item.getId(), item.getTitle(),
                item.getMediaid(), item.getRemark(),
                item.getType()
        );
        newItem.setAccountId(item.getAccountid());
        AccountInfo info = accountService.getAccountInfo(item.getAccountid());
        newItem.setAccount(info.getName());
        return newItem;
    }

    @Override
    public BaseResp sendToWx(int id) {
        return null;
    }

    @Override
    public BaseResp deleteArticle(List<Integer> data, boolean deleteWx) {
        BaseResp resp = new BaseResp();
        ArticlesExample exp = new ArticlesExample();
        ArticlesExample.Criteria c = exp.createCriteria();
        c.andIdIn(data);
        if (!deleteWx) {
            boolean s = articlesMapper.deleteByExample(exp) > 0;
            resp.setSuccess(s);
            return resp;
        }

        return resp;
    }
}
