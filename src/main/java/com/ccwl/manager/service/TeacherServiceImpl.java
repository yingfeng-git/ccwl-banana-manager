package com.ccwl.manager.service;

import com.ccwl.manager.dao.TeacherDao;
import com.ccwl.manager.model.Teacher;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service("teacherServiceImpl")
public class TeacherServiceImpl implements UserService{

    @Autowired
    HttpSession session;

    @Resource(name = "teacherDao")
    private TeacherDao teacherDao;

    public String AccountLogin(String number, String password){
        Teacher teacher = teacherDao.getAccountByNum(number, password);
        if (teacher == null){
            return "{\"state\": \"fail\", \"msg\": \"请检查账号密码输入是否正确！\"}";
        }else{
            this.session.setAttribute("USER", "teacher");
            return "{\"state\": \"success\", \"msg\": " + JSONObject.fromObject(teacher) + "}";
        }
    }

    public String AccountInfo(String number){
        Teacher teacher = teacherDao.getAccountInfoByNum(number);
        return "{\"state\": \"success\", \"msg\": " + JSONObject.fromObject(teacher) + "}";
    }

    public String remove(){
        this.session.removeAttribute("USER");
        return "{\"msg\": \"success\"}";
    }

    public String modifyPassword(String number, String password) {
        Object who = this.session.getAttribute("USER");
        int result = teacherDao.modifyPassword(number, password, who.toString());
        if (result == -1) {
            return "{\"state\": \"success\",\"msg\": \"找不到用户\"}";
        } else {
            return "{\"state\": \"success\"}";
        }
    }
}
