package com.enums;

/**
 * Created by admin on 2016/12/3.
 */
public enum MessageState {
    delete("删除", -1),
    delWx("从微信中删除", 0),
    normal("未发送", 1),
    send("发送成功", 2),
    sendFail("发送失败", 3),
    sendSuccess("发送成功", 4);

    private int value;
    private String name;

    MessageState(String name, int val) {
        value = val;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getName(int val) {
        switch (val) {
            case -1:
                return "删除";
            case 0:
                return "从微信删除";
            case 1:
                return "未发送";
            case 2:
                return "已发送";
            case 3:
                return "发送失败";
            case 4:
                return "发送成功";
        }
        return "";
    }
}
