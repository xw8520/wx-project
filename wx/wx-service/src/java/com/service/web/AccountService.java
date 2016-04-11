package com.service.web;

import com.models.web.AccountInfo;
import com.models.web.SaveAccount;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/4/10.
 */
public interface AccountService {
    Map<String, Object> getAccountList(int pageIndex, int pageSize, String args);

    Map<String, Object> getAccountHtml(int pageIndex, int pageSize, String args);

    Map<String, Object> addAccount(SaveAccount model);

    Map<String, Object> deleteAccount(List<Integer> list);

    SaveAccount getAccountById(int id);
}
