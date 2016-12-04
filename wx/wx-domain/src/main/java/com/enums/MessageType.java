package com.enums;

/**
 * Created by admin on 2016/12/3.
 */
public enum MessageType {
    news("图文消息", 1),
    text("文本消息", 2),
    image("图片消息", 3),
    voice("语音消息", 4);

    private int value;
    private String name;

    MessageType(String name, int val) {
        value = val;
        this.name = name;
    }

    public short getValue() {
        return (short) value;
    }

    public String getName() {
        return name;
    }

    public static String getName(int value) {
        if (value == 1) {
            return "图文消息";
        }
        if (value == 2) {
            return "文字消息";
        }
        if (value == 3) {
            return "图片消息";
        }
        if (value == 4) {
            return "语音消息";
        }
        return "未知";
    }
}
