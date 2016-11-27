package com.service.web.impl;

import com.data.ArticleItemMapper;
import com.data.ArticlesMapper;
import com.domain.wx.ArticleItem;
import com.domain.wx.ArticleItemExample;
import com.domain.wx.Articles;
import com.domain.wx.ArticlesExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.models.web.AccountInfo;
import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.media.AddArticleReq;
import com.models.web.media.ArticleInfo;
import com.models.wx.media.UploadArticleResp;
import com.models.wx.media.WxArticleItem;
import com.service.api.WxMediaService;
import com.service.api.impl.WxMediaServiceImpl;
import com.service.web.AccountService;
import com.service.web.ArticleService;
import com.utils.JsonUtils;
import com.utils.StringUtils;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
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
    org.slf4j.Logger logger = LoggerFactory.getLogger(WxMediaServiceImpl.class);
    @Resource
    ArticlesMapper articlesMapper;
    @Resource
    AccountService accountService;
    @Resource
    WxMediaService wxMediaService;
    @Resource
    ArticleItemMapper articleItemMapper;

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

    @Override
    public BaseResp sendArticleToWx(Integer id) {
        BaseResp resp = new BaseResp();
        Articles articles = articlesMapper.selectByPrimaryKey(id);
        if (articles == null) {
            resp.setInfo("素材不存在");
            return resp;
        }
        if (!StringUtils.isNullOrEmpty(articles.getMediaid())) {
            resp.setInfo("素材已经上传");
            return resp;
        }
        ArticleItemExample exp = new ArticleItemExample();
        ArticleItemExample.Criteria c = exp.createCriteria();
        c.andArticlesidEqualTo(id);
        List<ArticleItem> tmp = articleItemMapper.selectByExample(exp);
        if (tmp == null || tmp.size() == 0) {
            resp.setInfo("素材项不存在");
            return resp;
        }

        List<WxArticleItem> list = tmp.stream()
                .map(f -> wxArticleItemMap(f))
                .collect(Collectors.toList());
        try {
            UploadArticleResp upArticleResp = wxMediaService.uploadArticle(list, articles.getAccountid());
            if (upArticleResp.getErrcode() == 0) {
                logger.info("mediaId:%s wxMediaIdL%s", articles.getId(), upArticleResp.getMedia_id());
                logger.info(String.format("mediaId:%s wxMediaId:%s", articles.getId(), upArticleResp.getMedia_id()));
                Articles newArticle = new Articles();
                newArticle.setId(articles.getId());
                newArticle.setMediaid(upArticleResp.getMedia_id());
                ArticlesExample articleExp = new ArticlesExample();
                ArticlesExample.Criteria c2 = articleExp.createCriteria();
                c2.andIdEqualTo(articles.getId());
                boolean s = articlesMapper.updateByExampleSelective(newArticle, articleExp) > 0;
                resp.setSuccess(s);
                return resp;
            }
            resp.setInfo(upArticleResp.getErrmsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    private WxArticleItem wxArticleItemMap(ArticleItem item) {
        return new WxArticleItem(
                item.getThumbMediaId(), item.getAuthor(),
                item.getTitle(), item.getContentSourceUrl(),
                item.getContent(), item.getDigest(),
                item.getShowCoverPic() ? 1 : 0
        );
    }
}
