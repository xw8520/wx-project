package com.enums;

/**
 * Created by wangqing on 2016/2/24.
 *
 */
public enum QrCodeType {
    Tmp(0),LongInt(1),LongStr(2);

    private String value;
    QrCodeType(int val){
        if(val==0){
            value="QR_SCENE";
        }else if(val==1){
            value="QR_LIMIT_SCENE";
        }else{
            value="QR_LIMIT_STR_SCENE";
        }
    }

    public String getValue(){
        return value;
    }

}
