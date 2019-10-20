package com.ccwl.manager.service;

import com.ccwl.manager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{

    @Autowired
    HttpSession session;

    @Resource(name = "userDao")
    private com.ccwl.manager.dao.UserDao userDao;

    public String AccountLogin(String numb, String password){
        User user = userDao.getAccountByNum(numb, password);
        if (user == null){
            return "{\"state\": \"fail\", \"msg\": \"请检查账号密码输入是否正确！\"}";
        }else{
            this.session.setAttribute("USER", user);
            return "{\"state\": \"success\", \"msg\": " + user + "}";
        }
    }

    public String remove(){
        this.session.removeAttribute("USER");
        return "{\"msg\": \"success\"}";
    }

}
