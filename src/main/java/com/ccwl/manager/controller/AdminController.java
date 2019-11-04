package com.ccwl.manager.controller;


import com.ccwl.manager.service.AdminServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Resource(name = "adminServiceImpl")
    AdminServiceImpl adminServiceImpl;

    @RequestMapping(value="/uploadScore", produces="application/json;charset=UTF-8")
    @ResponseBody
    public String uploadScore(@RequestParam("file") CommonsMultipartFile file){
        return adminServiceImpl.uploadScore(file);
    }

    @RequestMapping(value="/uploadAttence", produces="application/json;charset=UTF-8")
    @ResponseBody
    public String uploadAttence(@RequestParam("file") CommonsMultipartFile file){
        return adminServiceImpl.uploadAttence(file);
    }

    @RequestMapping(value="/resetPassword", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String resetPassword(@RequestParam("who") String who,
                                @RequestParam("number") String number){
        return adminServiceImpl.resetPassword(who, number);
    }

    @RequestMapping(value="/modifyStudentInfo", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String modifyStudentInfo(@RequestParam("number") String number,
                                    @RequestParam("college") String college,
                                    @RequestParam("professional") String professional,
                                    @RequestParam("className") String className,
                                    @RequestParam("sex") String sex,
                                    @RequestParam("name") String name){
        return adminServiceImpl.modifyStudentInfo(number, college, professional, className,sex, name);
    }

    @RequestMapping(value="/modifyTeacherInfo", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String modifyTeacherInfo(@RequestParam("number") String number,
                                    @RequestParam("college") String college,
                                    @RequestParam("sex") String sex,
                                    @RequestParam("name") String name){
        return adminServiceImpl.modifyTeacherInfo(number, college, sex, name);
    }

    @RequestMapping(value="/insertTeacher", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String insertTeacher(@RequestParam("number") String number,
                                    @RequestParam("college") String college,
                                    @RequestParam("sex") String sex,
                                    @RequestParam("name") String name){
        return adminServiceImpl.insertTeacher(number, college, sex, name);
    }

    @RequestMapping(value="/insertStudent", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String insertStudent(@RequestParam("number") String number,
                                    @RequestParam("college") String college,
                                    @RequestParam("professional") String professional,
                                    @RequestParam("className") String className,
                                    @RequestParam("sex") String sex,
                                    @RequestParam("name") String name){
        return adminServiceImpl.insertStudent(number, college, professional, className,sex, name);
    }

    @RequestMapping(value="/createCourse", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String createCourse(@RequestParam("course") String course){
        return adminServiceImpl.insertCourse(course);
    }
}
