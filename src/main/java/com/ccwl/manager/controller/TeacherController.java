package com.ccwl.manager.controller;

import com.ccwl.manager.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    HttpSession session;

    @Resource(name="teacherServiceImpl")
    TeacherServiceImpl teacherService;

    @RequestMapping(value="/getScoreFromClassAndCourse", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getScoreFromClassAndCourse(@RequestParam(value="className") String className,
                                             @RequestParam(value="course") String course) {

        return teacherService.getScoreFromClassAndCourse(className, course);
    }

    @RequestMapping(value="/getAttenceFromClassAndCourse", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getAttenceFromClassAndCourse(@RequestParam(value="className") String className,
                                               @RequestParam(value="course") String course) {

        return teacherService.getAttenceFromClassAndCourse(className, course);
    }

    @RequestMapping(value="/getAttenceFromNumber", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getAttenceFromNumber(@RequestParam(value="number") String number) {
        return teacherService.getAttenceFromNumber(number);
    }

    @RequestMapping(value="/getClassFromClassName", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getClassFromClassName(@RequestParam(value="className") String className) {
        return teacherService.getClassFromClassName(className);
    }

}
