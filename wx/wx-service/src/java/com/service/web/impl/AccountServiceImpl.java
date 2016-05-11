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
import com.utils.XmlParseUtils;
import org.dom4j.Document;
import org.springframework.jmx.export.annotation.ManagedOperation;
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
    public Map<String, Object> getAccountHtml(int pageIndex, int pageSize, String args) {
        Map<String, Object> map=getAccountList(pageIndex,pageSize,args);
        if(map!=null){
            List<AccountInfo> list = (List<AccountInfo>) map.get("data");
            if(list!=null){
                try {
                    InputStream stream = this.getClass().getResource("/template/accountList.xml")
                            .openStream();
                    Document doc = XmlParseUtils.getDocument(stream);
                    String str = XmlParseUtils.getDocElementTextByPath(doc, "root/body");
                    if (!StringUtils.isNullOrEmpty(str)) {
                        StringBuffer buffer = new StringBuffer();
                        if (list != null) {
                            for (AccountInfo item : list) {
                                String tmp = MessageFormat.format(str, item.getId(), item.getName(),
                                        item.getAppid(), item.getType(), item.getId());
                                buffer.append(tmp);
                            }
                        }
                        map.remove("data");
                        map.put("data", buffer.toString());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }else{
            map.put("total",0);
            map.put("data","");
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
}
