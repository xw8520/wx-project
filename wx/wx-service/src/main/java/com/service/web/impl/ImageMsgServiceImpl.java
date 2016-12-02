package com.service.web.impl;

import com.data.ImageMessageMapper;
import com.domain.wx.ImageMessage;
import com.domain.wx.ImageMessageExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.models.web.*;
import com.models.web.media.AddImageMsgReq;
import com.models.web.media.ImageMsgInfo;
import com.service.api.inter.WxMediaService;
import com.service.web.inter.AccountService;
import com.service.web.inter.ImageMsgService;
import com.utils.JsonUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by admin on 2016/11/26.
 */
@Service("imageMsgService")
public class ImageMsgServiceImpl implements ImageMsgService {

    @Resource
    WxMediaService wxMediaService;
    @Resource
    ImageMessageMapper imgMsgMapper;
    @Resource
    AccountService accountService;

    @Override
    public BaseResp addImageMsg(AddImageMsgReq req) {
        BaseResp resp = new BaseResp();
        try {
            String url = wxMediaService.uploadImage(req.getFileName(), req.getAccountId());
            if (StringUtils.isEmpty(url)) {
                resp.setInfo("保存失败");
                return resp;
            }
            ImageMessage img = new ImageMessage();
            img.setAccountid(req.getAccountId());
            img.setDomain(req.getDomain());
            img.setCreatetime(new Date());
            img.setTitle(req.getTitle());
            img.setUrl(url);
            img.setRemark(req.getRemark());

            boolean success = imgMsgMapper.insert(img) == 1;
            resp.setSuccess(success);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setInfo("系统出错");
        }
        return resp;
    }

    @Override
    public DataListResp getImageMsgList(int pageSize, int pageIndex, int domain, String args) {
        DataListResp resp = new DataListResp();

        try {
            ImageMessageExample exp = new ImageMessageExample();
            ImageMessageExample.Criteria criteria = exp.createCriteria();

            Map<String, String> param = (HashMap<String, String>) JsonUtils.Deserialize(args, HashMap.class);
            if (param.containsKey("title")) {
                criteria.andTitleLike("%" + param.get("title") + "%");
            }
            if (param.containsKey("accountId")) {
                criteria.andAccountidEqualTo(Integer.valueOf(param.get("accountId")));
            }
            List<ImageMsgInfo> list = new ArrayList<>();
            PageHelper.startPage(pageIndex, pageSize);
            List<ImageMessage> tmp = imgMsgMapper.selectByExample(exp);
            PageInfo<ImageMessage> pageInfo = new PageInfo<>(tmp);
            resp.setTotal(pageInfo.getTotal());
            ImageMsgInfo newItem;
            AccountInfo accountInfo;
            for (ImageMessage item : tmp) {
                newItem = new ImageMsgInfo(item.getId(),
                        item.getTitle(), item.getRemark(),
                        item.getUrl());
                accountInfo = accountService.getAccountInfo(item.getAccountid());
                newItem.setAccount(accountInfo != null ? accountInfo.getName() : "");
                list.add(newItem);
            }
            resp.setList(list);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return resp;
    }

    @Override
    public BaseResp deleteImageMsg(List<Integer> data) {
        ImageMessageExample exp = new ImageMessageExample();
        ImageMessageExample.Criteria criteria = exp.createCriteria();
        criteria.andIdIn(data);
        BaseResp resp = new BaseResp();
        boolean success = imgMsgMapper.deleteByExample(exp) > 0;
        resp.setSuccess(success);
        return resp;
    }
}
