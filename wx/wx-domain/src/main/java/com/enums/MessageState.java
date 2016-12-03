package com.enums;

/**
 * Created by admin on 2016/12/3.
 */
public enum MessageState {
    delete(-1),
    delWx(0),
    normal(1),
    send(3),
    sendFail(4),
    sendSuccess(5);

    private int value;

    MessageState(int val) {
        value = val;
    }

    public int getValue() {
        return value;
    }

    public static String getName(int val) {
        switch (val) {
            case 0:
                return "删除";
            case 1:
                return "从微信删除";
            case 2:
                return "未发送";
            case 3:
                return "已发送";
            case 4:
                return "发送失败";
            case 5:
                return "发送成功";
        }
        return "";
    }
}
