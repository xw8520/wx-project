package com.controller;

import com.model.PagerParam;
import com.models.web.AccountInfo;
import com.models.web.SaveAccount;
import com.service.web.AccountService;
import com.utils.CookieUtil;
import com.utils.StringUtils;
import com.utils.XmlParseUtils;
import org.dom4j.Document;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
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
    public Map<String, Object> getAccountList(@ModelAttribute("data") PagerParam data) {
        Map<String, Object> map = accountService.getAccountHtml(data.getPageIndex(),
                data.getPageSize(), data.getArgs());
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "addAccount", method = RequestMethod.POST)
    public Map<String, Object> addAccount(@ModelAttribute("data") SaveAccount data) {
        Map<String, Object> map = accountService.addAccount(data);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "deleteAccount", method = RequestMethod.POST)
    public Map<String, Object> deleteAccount(@RequestBody List<Integer> data) {
        Map<String, Object> map = accountService.deleteAccount(data);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "getAccount", method = RequestMethod.POST)
    public SaveAccount getAccount(@RequestParam("id") int id) {
        SaveAccount account = accountService.getAccountById(id);
        return account;
    }
}
