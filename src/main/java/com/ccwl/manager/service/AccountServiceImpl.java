package com.ccwl.manager.service;

import com.ccwl.manager.model.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("AccountServiceImpl")
public class AccountServiceImpl {

    @Resource(name = "userDao")
    private com.ccwl.manager.dao.userDao userDao;


    public String AccountLogin(String numb, String password){
        Account account = userDao.getAccountByNum(numb, password);
        if (account == null){
            return "{\"state\": \"fail\", \"msg\": \"请检查账号密码输入是否正确！\"}";
        }else{
            return "{\"state\": \"success\", \"msg\": " + account + "}";
        }
    }
}
