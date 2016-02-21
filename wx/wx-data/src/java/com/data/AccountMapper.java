package com.data;

import com.domain.wx.Account;

/**
 * Created by Admin on 2016/2/21.
 */
public interface AccountMapper {
    Account getAccountById(int id);

    void addAccount(Account account);
}
