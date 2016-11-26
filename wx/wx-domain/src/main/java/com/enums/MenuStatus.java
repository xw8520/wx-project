package com.enums;

/**
 * Created by TimLin on 2016/5/11.
 */
public enum MenuStatus {
    刪除(0),正常(1),锁定(2);

    MenuStatus(int val){
        this.val=val;
    }
    private int val;
    public byte getValue(){
        return (byte)val;
    }
}
