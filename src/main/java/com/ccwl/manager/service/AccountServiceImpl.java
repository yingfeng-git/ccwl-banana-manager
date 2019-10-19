package com.ccwl.manager.service;

import com.ccwl.manager.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service("AccountServiceImpl")
public class AccountServiceImpl {

    @Autowired
    HttpSession session;

    @Resource(name = "userDao")
    private com.ccwl.manager.dao.userDao userDao;

    public String AccountLogin(String numb, String password){
        Account account = userDao.getAccountByNum(numb, password);
        if (account == null){
            return "{\"state\": \"fail\", \"msg\": \"请检查账号密码输入是否正确！\"}";
        }else{
            this.session.setAttribute("USER", account);
            return "{\"state\": \"success\", \"msg\": " + account + "}";
        }
    }

    public String remove(){
        this.session.removeAttribute("USER");
        return "{\"msg\": \"success\"}";
    }

}
