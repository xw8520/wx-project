package com.service.web.impl;

import com.data.AccountMapper;
import com.domain.wx.Account;
import com.enums.AccountType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.models.web.AccountInfo;
import com.models.web.SaveAccount;
import com.service.web.AccountService;
import com.utils.JsonUtils;
import com.utils.StringUtils;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by Admin on 2016/4/10.
 */
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountMapper accountMapper;

    @Override
    public Map<String, Object> getAccountList(int pageIndex, int pageSize, String args) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageIndex, pageSize, true);

        try {
            Map<String, Object> param = null;
            if (!StringUtils.isNullOrEmpty(args)) {
                param = JsonUtils.Deserialize(args, HashMap.class);
            }
            PageHelper.startPage(pageIndex, pageSize);
            List<Account> list = accountMapper.getAccountList(param);
            PageInfo page = new PageInfo(list);
            map.put("total", page.getTotal());
            List<AccountInfo> tmp = new ArrayList<>();
            AccountInfo info;
            for (Account item : list) {
                info = new AccountInfo(item.getId(), item.getName(),
                        item.getAppid(), item.getSecret(),
                        AccountType.values()[item.getType()]);
                tmp.add(info);
            }

            map.put("data", tmp);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return map;
    }

    @Override
    public Map<String, Object> addAccount(SaveAccount model) {
        Map<String, Object> map = new HashMap<>();
        if (model == null) {
            map.put("success", false);
            map.put("info", "数据为空");
            return map;
        }
        Account account = new Account();
        account.setRemark(model.getRemark());
        account.setAppid(model.getAppid());
        account.setCreatetime(new Date());
        account.setId(model.getId());
        account.setModifytime(new Date());
        account.setName(model.getName());
        account.setSecret(model.getSecret());
        account.setType(model.getType());
        try {
            if (account.getId() != 0) {
                accountMapper.updateAccount(account);
            } else {
                accountMapper.addAccount(account);
            }
            map.put("success", true);
        } catch (Exception ex) {
            ex.printStackTrace();
            map.put("success", false);
            map.put("info", "系统出错");
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteAccount(List<Integer> list) {

        Map<String, Object> map = new HashMap<>();
        if (map == null) {
            map.put("success", false);
            map.put("info", "数据为空");
            return map;
        }
        Map<String, List<Integer>> param = new HashMap<>();
        param.put("list", list);
        try {
            accountMapper.deleteAccount(param);
            map.put("success", true);
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
            map.put("success", false);
            map.put("info", "系统出错");
            return map;
        }

    }
}
