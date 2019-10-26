package com.ccwl.manager.service;

import com.ccwl.manager.dao.ClassTableDao;
import com.ccwl.manager.model.Course;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service("classTableServiceImpl")
public class ClassTableServiceImpl {
    @Autowired
    HttpSession session;

    @Resource(name = "classTableDao")
    private ClassTableDao classTableDao;

    public String CourseSelect(String number) {
        List<Course> courseList = classTableDao.getCourseByClassId(number,session.getAttribute("USER").toString());
        if (courseList == null){
            return "{\"state\": \"fail\", \"msg\": \"请检查账号是否正确！\"}";
        }else{
            this.session.setAttribute("USER", JSONArray.fromObject(courseList));
            return "{\"state\": \"success\", \"msg\": " + JSONArray.fromObject( courseList) + "}";
        }
    }



}
