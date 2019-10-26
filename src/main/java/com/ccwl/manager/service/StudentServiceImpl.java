package com.ccwl.manager.service;

import com.ccwl.manager.dao.StudentDao;
import com.ccwl.manager.model.Student;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service("studentServiceImpl")
public class StudentServiceImpl implements UserService{

    @Autowired
    HttpSession session;

    @Resource(name = "studentDao")
    private StudentDao studentDao;

    public String AccountLogin(String number, String password){
        Student student = studentDao.getAccountByNum(number, password);
        if (student == null){
            return "{\"state\": \"fail\", \"msg\": \"请检查账号密码输入是否正确！\"}";
        }else{
            this.session.setAttribute("USER", "student");
            return "{\"state\": \"success\", \"msg\": " + JSONObject.fromObject(student) + "}";
        }
    }

    public String AccountInfo(String number){
        Student student = studentDao.getAccountInfoByNum(number);
        return "{\"state\": \"success\", \"msg\": " + JSONObject.fromObject(student) + "}";
    }

    public String modifyPassword(String number, String password){
        Object who = this.session.getAttribute("USER");
        int result = studentDao.modifyPassword(number, password, who.toString());
        if (result == -1) {
            return "{\"state\": \"success\",\"msg\": \"找不到用户\"}";
        } else {
            return "{\"state\": \"success\"}";
        }
    }

    public String remove(){
        this.session.removeAttribute("USER");
        return "{\"msg\": \"success\"}";
    }

}
