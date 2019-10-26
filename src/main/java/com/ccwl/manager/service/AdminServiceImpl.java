package com.ccwl.manager.service;

import com.ccwl.manager.dao.AdminDao;
import com.ccwl.manager.model.User;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service("adminServiceImpl")
public class AdminServiceImpl implements UserService {

    @Autowired
    HttpSession session;

    @Resource(name = "adminDao")
    private AdminDao adminDao;

    public String AccountLogin(String number, String password) {
        User user = adminDao.getAccountByNum(number, password);
        if (user == null){
            return "{\"state\": \"fail\", \"msg\": \"请检查账号密码输入是否正确！\"}";
        }else{
            this.session.setAttribute("USER", "admin");
            return "{\"state\": \"success\", \"msg\": " + JSONObject.fromObject(user) + "}";
        }
    }

    public String modifyInfo(Object[] msg) {
        Object who = this.session.getAttribute("USER");
        new User();
        int result = this.adminDao.modifyInfo(msg, who.toString());
        if (result == -1) {
            return "{\"state\": \"success\",\"msg\": \"找不到用户\"}";
        } else {
            return "{\"state\": \"success\"}";
        }
    }

    public String remove() {
        this.session.removeAttribute("USER");
        return "{\"msg\": \"success\"}";
    }
}
