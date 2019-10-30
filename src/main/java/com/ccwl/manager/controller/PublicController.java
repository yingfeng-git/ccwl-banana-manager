package com.ccwl.manager.controller;


import com.ccwl.manager.service.PublicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class PublicController {
    @Autowired
    HttpSession session;

    @Resource(name = "publicServiceImpl")
    PublicServiceImpl publicService;

    @RequestMapping(value="/getCourse", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getCourse() {
        return publicService.getCourse();
    }

    @RequestMapping(value="/getCollege", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getCollege() {
        return publicService.getCollege();
    }

    @RequestMapping(value="/getTeacher", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getTeacher() {
        return publicService.getTeacher();
    }


}
