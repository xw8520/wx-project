package com.dto.wx.enums;

/**
 * Created by Admin on 2016/3/13.
 * 临时素材类型
 */
public enum TmpMediaType {
    image(0),
    voice(1),
    video(2),
    thumb(3),
    unknow(4);

    private byte val;

    TmpMediaType(int val) {
        this.val = (byte) val;
    }

    public byte getValue() {
        return val;
    }
}
