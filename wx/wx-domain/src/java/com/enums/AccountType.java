package com.enums;

/**
 * Created by Admin on 2016/4/10.
 */
public enum AccountType {
    订阅号((byte) 0), 服务号((byte) 1);

    private byte val;

    AccountType(byte val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
