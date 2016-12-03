package com.enums;

/**
 * Created by admin on 2016/12/3.
 */
public enum MessageType {
    news(0),
    text(1),
    image(2),
    voice(3);

    private int value;

    MessageType(int val) {
        value = val;
    }

    public short getValue() {
        return (short) value;
    }

    public static String getName(int value){
        if(value==0){
            return "图文消息";
        }
        if(value==1){
            return "文字消息";
        }
        if(value==2){
            return "图片消息";
        }
        if(value==3){
            return "语音消息";
        }
        return "未知";
    }
}
