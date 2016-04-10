package com.data;

import com.domain.wx.Account;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/2/21.
 */
public interface AccountMapper {
    Account getAccountById(int id);

    void addAccount(Account account);

    void updateAccount(Account account);

    List<Account> getAccountList(Map<String, Object> map);

    void deleteAccount(Map<String, List<Integer>> map);
}
