package com.service.web.impl;

import com.cache.RedisCacheManager;
import com.cache.RedisKeys;
import com.data.AccountMapper;
import com.domain.wx.Account;
import com.enums.AccountType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.models.web.*;
import com.service.web.AccountService;
import com.utils.JsonUtils;
import com.utils.StringUtils;
import com.utils.XmlParseUtils;
import org.dom4j.Document;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by Admin on 2016/4/10.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountMapper accountMapper;
    @Resource
    RedisCacheManager redisCacheManager;

    @Override
    public DataListResp getAccountList(int pageIndex, int pageSize, String args) {
        DataListResp resp = new DataListResp();
        try {
            Map<String, Object> param = null;
            if (!StringUtils.isNullOrEmpty(args)) {
                param = JsonUtils.Deserialize(args, HashMap.class);
            }
            PageHelper.startPage(pageIndex, pageSize);
            List<Account> list = accountMapper.getAccountList(param);
            PageInfo page = new PageInfo(list);
            resp.setTotal(page.getTotal());
            List<AccountInfo> tmp = new ArrayList<>();
            AccountInfo info;
            for (Account item : list) {
                info = new AccountInfo(item.getId(), item.getName(),
                        item.getAppid(), item.getSecret(),
                        AccountType.values()[item.getType()]);
                tmp.add(info);
            }
            resp.setList(tmp);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

//    @Override
//    public Map<String, Object> getAccountHtml(int pageIndex, int pageSize, String args) {
//        DataListResp map = getAccountList(pageIndex, pageSize, args);
//        if (map != null) {
//            List<AccountInfo> list = map.getList();
//            if (list != null) {
//                try {
//                    InputStream stream = this.getClass().getResource("/template/accountList.xml")
//                            .openStream();
//                    Document doc = XmlParseUtils.getDocument(stream);
//                    String str = XmlParseUtils.getDocElementTextByPath(doc, "root/body");
//                    if (!StringUtils.isNullOrEmpty(str)) {
//                        StringBuffer buffer = new StringBuffer();
//                        if (list != null) {
//                            for (AccountInfo item : list) {
//                                String tmp = MessageFormat.format(str, item.getId(), item.getId(), item.getName(),
//                                        item.getAppid(), item.getType());
//                                buffer.append(tmp);
//                            }
//                        }
//                        map.remove("data");
//                        map.put("list", buffer.toString());
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        } else {
//            map.put("total", 0);
//            map.put("data", "");
//        }
//        return map;
//    }

    @Override
    public BaseResp addAccount(SaveAccount model, UserInfo user) {
        BaseResp resp = new BaseResp();
        if (model == null) {
            resp.setSuccess(false);
            resp.setInfo("数据为空");
            return resp;
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
        account.setDomain(user.getDomain());
        try {
            if (account.getId() != 0) {
                accountMapper.updateAccount(account);
            } else {
                accountMapper.addAccount(account);
            }
            resp.setSuccess(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setInfo("系统出错");
        }
        return resp;
    }

    @Override
    public BaseResp deleteAccount(List<Integer> list) {
        BaseResp resp = new BaseResp();
        Map<String, List<Integer>> param = new HashMap<>();
        param.put("list", list);
        try {
            accountMapper.deleteAccount(param);
            resp.setSuccess(true);
            return resp;
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setInfo("系统出错");
            return resp;
        }

    }

    @Override
    public SaveAccount getAccountById(int id) {
        SaveAccount info = new SaveAccount();
        Account account = accountMapper.getAccountById(id);
        if (account != null) {
            info.setId(account.getId());
            info.setName(account.getName());
            info.setAppid(account.getAppid());
            info.setSecret(account.getSecret());
            info.setType(account.getType());
            info.setRemark(account.getRemark());
        }

        return info;
    }

    @Override
    public AccountInfo getAccountInfo(int id) {
        String key = String.format(RedisKeys.accountInfoKey, id);
        AccountInfo info = redisCacheManager.get(key, AccountInfo.class);
        if (info == null) {
            Account account = accountMapper.getAccountById(id);
            info = new AccountInfo();
            info.setType(AccountType.getType(account.getType()));
            info.setAppid(account.getAppid());
            info.setId(account.getId());
            info.setName(account.getName());
            info.setSecret(account.getSecret());
            redisCacheManager.put(key, info, 30 * 60);
        }

        return info;
    }

    @Override
    public List<AccountSelectInfo> getAccountSelect(int domain) {
        return accountMapper.getAccountSelect(domain);
    }
}
