package com.ccwl.manager.service;

import com.ccwl.manager.dao.AdminDao;
import com.ccwl.manager.model.User;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service("adminServiceImpl")
public class AdminServiceImpl implements UserService {

    @Autowired
    HttpSession session;

    @Resource(name = "adminDao")
    private AdminDao adminDao;

    private static final String[] HEAD_SCORE = {"学号", "姓名", "成绩","课程名字"};
    private static final String[] HEAD_ATTENCE = {"学号","学生名称", "课程名称",	"教师名称",	"教室",	"周数",	"星期",	"节数",	"考勤情况"};


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
            return "{\"state\": \"fail\",\"msg\": \"找不到用户\"}";
        } else {
            return "{\"state\": \"success\"}";
        }
    }

    public String remove() {
        this.session.removeAttribute("USER");
        return "{\"msg\": \"success\"}";
    }

    public String uploadScore(CommonsMultipartFile file) {
        try {
            List<ArrayList<Object>> result = ExcelHandle.loadExcel(HEAD_SCORE, file);
            if (result == null){
                return "{\"state\": \"fail\", \"msg\": \"文件校验不通过，请确认文件是否为.xlsx文件，以及表头为"+
                        Arrays.toString(HEAD_SCORE) +"}";
            }
            int count = adminDao.insertScore(result);
            return "{\"state\": \"success\", \"msg\": \"成功插入数据" + count + "条\"}";
        } catch (IOException e) {
            return "{\"state\": \"fail\"}";
        }
    }

    public String uploadAttence(CommonsMultipartFile file) {
        try {
            List<ArrayList<Object>> result = ExcelHandle.loadExcel(HEAD_ATTENCE, file);
            if (result == null){
                return "{\"state\": \"fail\", \"msg\": \"文件校验不通过，请确认文件是否为.xlsx文件，以及表头为"+
                        Arrays.toString(HEAD_ATTENCE) +"}";
            }
            int count = adminDao.uploadAttence(result);
            return "{\"state\": \"success\", \"msg\": \"成功插入数据" + count + "条\"}";
        } catch (IOException e) {
            return "{\"state\": \"fail\"}";
        }
    }

    public String resetPassword(String who, String number) {
        try {
            this.session.getAttribute("USER").toString();
            int result = this.adminDao.resetPassword(who, number);
            if (result != 1) {
                return "{\"state\": \"fail\",\"msg\": \"找不到用户\"}";
            } else {
                return "{\"state\": \"success\"}";
            }
        }catch (NullPointerException E){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }

    public String modifyStudentInfo(String number, String college, String professional, String className, String sex, String name) {
        try {
            this.session.getAttribute("USER").toString();
            int result = this.adminDao.modifyStudentInfo(number, college, professional, className,sex, name);
            if (result != 1) {
                return "{\"state\": \"fail\",\"msg\": \"找不到用户\"}";
            } else {
                return "{\"state\": \"success\"}";
            }
        }catch (NullPointerException E){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }

    public String modifyTeacherInfo(String number, String college, String sex, String name) {
        try {
            this.session.getAttribute("USER").toString();
            int result = this.adminDao.modifyTeacherInfo(number, college,sex, name);
            if (result != 1) {
                return "{\"state\": \"fail\",\"msg\": \"找不到用户\"}";
            } else {
                return "{\"state\": \"success\"}";
            }
        }catch (NullPointerException E){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }

    public String insertTeacher(String number, String college, String sex, String name) {
        try {
            this.session.getAttribute("USER").toString();
            int result = this.adminDao.insertTeacher(number, college, sex, name);
            if (result == -1) {
                return "{\"state\": \"fail\",\"msg\": \"用户已存在，请检查number\"}";
            } else {
                return "{\"state\": \"success\",\"msg\": \"用户创建成功\"}";
            }
        }catch (NullPointerException E){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }

    public String insertStudent(String number, String college, String professional, String className, String sex, String name) {
        try {
            this.session.getAttribute("USER").toString();
            int result = this.adminDao.insertStudent(number, college, professional, className,sex, name);
            if (result == -1) {
                return "{\"state\": \"fail\",\"msg\": \"用户已存在，请检查number\"}";
            } else {
                return "{\"state\": \"success\",\"msg\": \"用户创建成功\"}";
            }
        }catch (NullPointerException E){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }

    public String insertCourse(String course) {
        try {
            this.session.getAttribute("USER").toString();
            this.adminDao.insertCourse(course);
            return "{\"state\": \"success\",\"msg\": \"课程创建成功\"}";
        }catch (NullPointerException E){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }
}
