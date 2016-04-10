package com.controller;

import com.model.PagerParam;
import com.models.web.AccountInfo;
import com.models.web.SaveAccount;
import com.service.web.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.HashMap;
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
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("account/index");
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "getAccountList", method = RequestMethod.POST)
    public Map<String, Object> getAccountList(@ModelAttribute("data") PagerParam data) {
        Map<String, Object> map = accountService.getAccountList(data.getPageIndex(),
                data.getPageSize(), data.getArgs());
        List<AccountInfo> list = (List<AccountInfo>) map.get("data");
        String str = "<tr>\n" +
                "            <td><input class=\"chkId\" val=\"{4}\" type=\"checkbox\"/></td>\n" +
                "            <td>{0}</td>\n" +
                "            <td>{1}</td>\n" +
                "            <td>{2}</td>\n" +
                "            <td>{3}</td>\n" +
                "        </tr>";
        StringBuffer buffer = new StringBuffer();
        if (list != null) {
            for (AccountInfo item : list) {
                String tmp = MessageFormat.format(str, item.getId(), item.getName(),
                        item.getAppid(), item.getType(), item.getId());
                buffer.append(tmp);
            }
        }
        map.remove("data");
        map.put("data", buffer.toString());

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
}
