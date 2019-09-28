package com.ccwl.manager.controller;

import com.ccwl.manager.service.AccountServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Resource(name = "AccountServiceImpl")
    private AccountServiceImpl accountServiceImpl;

    @RequestMapping(value="/login", method= RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String loginByPostFromMysql(@RequestParam(value="numb") String numb,
                                       @RequestParam(value="password") String password) {
        return accountServiceImpl.AccountLogin(numb, password);
    }
}
