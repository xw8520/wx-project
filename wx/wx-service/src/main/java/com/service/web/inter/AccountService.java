package com.service.web.inter;

import com.models.web.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/4/10.
 */
public interface AccountService {
     DataListResp getAccountList(int pageIndex, int pageSize, String args);

//    Map<String, Object> getAccountHtml(int pageIndex, int pageSize, String args);

    BaseResp addAccount(SaveAccount model, UserInfo user);

    BaseResp deleteAccount(List<Integer> list);

    SaveAccount getAccountById(int id);

    AccountInfo getAccountInfo(int id);

    List<AccountSelectInfo> getAccountSelect(int domain);
}
