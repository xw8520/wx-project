package com.data;

import com.domain.wx.Jsapiticket;

/**
 * Created by Admin on 2016/2/28.
 */
public interface JsapiticketMapper {
    /**
     * @param id
     * @return
     */
    Jsapiticket getTicketById(int id);

    /**
     *
     * @param accountid
     * @return
     */
    Jsapiticket getTicketByAccount(int accountid);

    /**
     * @param ticket
     */
    void addTicket(Jsapiticket ticket);
}
