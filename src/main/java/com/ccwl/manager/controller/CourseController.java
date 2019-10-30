package com.ccwl.manager.controller;


import com.ccwl.manager.service.ClassTableServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/course")
@CrossOrigin
public class CourseController {
    @Autowired
    HttpSession session;


    @Resource(name = "classTableServiceImpl")
    private ClassTableServiceImpl classTableServiceImpl;


    @RequestMapping(value="/getCourseInfo", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    @ResponseBody
    public String getCourseInfo(@RequestParam(value="number") String number) {
        try {
            this.session.getAttribute("USER").toString();
            return classTableServiceImpl.CourseSelect(number);
        } catch (NullPointerException e) {
            return "{\"msg\": \"用户未登陆！\"}";
        }
    }
}
