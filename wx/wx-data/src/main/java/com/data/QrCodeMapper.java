package com.data;

import com.domain.wx.QrCode;

/**
 * Created by wangqing on 2016/2/24.
 */
public interface QrCodeMapper {
    /**
     *
     * @param id
     * @return
     */
    QrCode getCodeById(int id);

    /**
     *
     * @param param
     * @return
     */
    QrCode getCodeByParam(String param);

    /**
     *
     * @param code
     */
    void  addQrCode(QrCode code);


    void updateQrCode(QrCode code);
}
