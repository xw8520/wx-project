package com.controller;

import com.model.PagerParam;
import com.models.web.BaseResp;
import com.models.web.DataListResp;
import com.models.web.SaveAccount;
import com.models.web.UserInfo;
import com.service.web.AccountService;
import com.utils.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/4/8.
 */
@Controller
@RequestMapping("account")
public class AccountController {

    @Resource
    AccountService accountService;

    @RequestMapping(value = "index.html")
    public ModelAndView index(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView view = new ModelAndView("account/index");
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "getAccountList", method = RequestMethod.POST)
    public DataListResp getAccountList(PagerParam data) {
        DataListResp map = accountService.getAccountList(data.getPageIndex(),
                data.getPageSize(), data.getArgs());
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "addAccount", method = RequestMethod.POST)
    public BaseResp addAccount(SaveAccount data,
                               HttpServletRequest req) {
        UserInfo user = CookieUtil.GetCurrentUser(req);
        BaseResp map = accountService.addAccount(data, user);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "deleteAccount", method = RequestMethod.POST)
    public BaseResp deleteAccount(@RequestBody List<Integer> data) {
        BaseResp map = accountService.deleteAccount(data);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "getAccount", method = RequestMethod.POST)
    public SaveAccount getAccount(@RequestParam("id") int id) {
        SaveAccount account = accountService.getAccountById(id);
        return account;
    }
}
