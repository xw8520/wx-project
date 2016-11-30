package com.service.web.impl;

import com.data.WxTagMapper;
import com.domain.wx.WxTag;
import com.domain.wx.WxTagExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.models.web.AccountInfo;
import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.tag.AddTagReq;
import com.models.web.tag.DeleteTagReq;
import com.models.web.tag.WxTagInfo;
import com.models.wx.WxBaseResp;
import com.models.wx.tag.CreateTagReq;
import com.models.wx.tag.CreateTagResp;
import com.models.wx.tag.UpdateTagReq;
import com.service.api.WxTagService;
import com.service.web.AccountService;
import com.service.web.TagService;
import com.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by admin on 2016/11/30.
 */
@Service("tagService")
public class TagServiceImpl implements TagService {
    Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);
    @Resource
    WxTagService wxTagService;
    @Resource
    WxTagMapper wxTagMapper;
    @Resource
    AccountService accountService;

    @Override
    public BaseResp addTag(AddTagReq req) {
        BaseResp resp = new BaseResp();
        try {
            WxTag tag = new WxTag();
            if (req.getTagId() > 0) {
                tag.setId(req.getId());
                tag.setName(req.getName());
                tag.setRemark(req.getRemark());
                WxTagExample exp = new WxTagExample();
                WxTagExample.Criteria c = exp.createCriteria();
                c.andIdEqualTo(req.getId());
                boolean s = wxTagMapper.updateByExampleSelective(tag, exp) > 0;

                UpdateTagReq updateTagReq = new UpdateTagReq();
                updateTagReq.setName(req.getName());
                updateTagReq.setTagId(req.getTagId());
                updateTagReq.setAccountId(req.getAccountId());
                WxBaseResp wxBaseResp = wxTagService.updateTag(updateTagReq);
                resp.setSuccess(wxBaseResp.getErrcode() == 0 && s);
            } else {
                CreateTagResp createTagResp = wxTagService.createTag(req.getName(),
                        req.getAccountId());
                if (createTagResp.getErrcode() == 0
                        && createTagResp.getTag() != null) {
                    tag.setId(req.getId());
                    tag.setName(req.getName());
                    tag.setRemark(req.getRemark());
                    tag.setCreatetime(new Date());
                    tag.setWxid(createTagResp.getTag().getId());
                    tag.setAccountid(req.getAccountId());
                    tag.setDomain(req.getDomain());
                    boolean s = wxTagMapper.insert(tag) > 0;
                    resp.setSuccess(s);
                } else {
                    resp.setInfo("添加标签出错");
                }
            }
        } catch (Exception ex) {
            logger.error("addTag", ex);
        }
        return resp;
    }

    @Override
    public WxTagInfo getTag(int tagId) {
        WxTag tag = wxTagMapper.selectByPrimaryKey(tagId);
        return wxTagInfoMap(tag);
    }

    @Override
    public DataListResp getTagList(int pageSize, int pageIndex, int domain, String args) {
        DataListResp resp = new DataListResp();
        try {
            WxTagExample exp = new WxTagExample();
            WxTagExample.Criteria c = exp.createCriteria();
            c.andDomainEqualTo(domain);
            Map<String, String> param = (HashMap<String, String>) JsonUtils.Deserialize(args, HashMap.class);
            if (param.containsKey("accountId") && !param.get("accountId").equals("-1")) {
                c.andAccountidEqualTo(Integer.valueOf(param.get("accountId")));
            }
            if (param.containsKey("name")) {
                c.andNameLike("%" + param.get("name") + "%");
            }
            PageHelper.startPage(pageIndex, pageSize);
            List<WxTag> tmp = wxTagMapper.selectByExample(exp);
            PageInfo page = new PageInfo(tmp);
            resp.setTotal(page.getTotal());
            List list = tmp.stream().map(f -> wxTagInfoMap(f)).collect(Collectors.toList());
            resp.setList(list);
        } catch (Exception ex) {
            logger.error("getTagList", ex);
        }
        return resp;
    }

    private WxTagInfo wxTagInfoMap(WxTag tag) {
        WxTagInfo tagInfo = new WxTagInfo(tag.getId(), tag.getWxid(), tag.getName(), tag.getRemark(), tag.getAccountid());
        AccountInfo accountInfo = accountService.getAccountInfo(tag.getAccountid());
        if (accountInfo != null) {
            tagInfo.setAccountName(accountInfo.getName());
        }
        return tagInfo;
    }

    @Override
    public BaseResp deleteTag(DeleteTagReq req) {
        return null;
    }
}
