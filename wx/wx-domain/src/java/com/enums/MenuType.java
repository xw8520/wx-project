package com.enums;

/**
 * Created by TimLin on 2016/5/11.
 */
public enum  MenuType {
    Group(0),Url(1);

    MenuType(int val){
        this.val=val;
    }
    private int val;
    public byte getValue(){
        return (byte)val;
    }
}
