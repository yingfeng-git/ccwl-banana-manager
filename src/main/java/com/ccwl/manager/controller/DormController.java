package com.ccwl.manager.controller;


import com.ccwl.manager.service.DormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/dorm")
public class DormController {
    @Autowired
    HttpSession session;

    @Resource(name = "dormServiceImpl")
    private DormServiceImpl dormServiceImpl;

    @RequestMapping(value="/getDormByNum", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getSchoolCardInfo(@RequestParam(value="number") String number) {
        return dormServiceImpl.getDormByNum(number);
    }

    @RequestMapping(value="/getDormByClassName", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getDormByClassName(@RequestParam(value="className") String className) {
        return dormServiceImpl.getDormByClassName(className);
    }

    @RequestMapping(value="/commitVisitorMsg", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String commitVisitorMsg(@RequestParam(value="number") String number,
                                   @RequestParam(value="msg") String msg) {
        return dormServiceImpl.setVisitorMsg(number, msg);
    }

    @RequestMapping(value="/commitDormFeedback", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String commitDormFeedback(@RequestParam(value="number") String number,
                                     @RequestParam(value="msg") String msg) {
        return dormServiceImpl.setDormFeedback(number, msg);
    }

    @RequestMapping(value="/getVisitorMsg", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getVisitorMsg() {
        return dormServiceImpl.getVisitorMsg();
    }

    @RequestMapping(value="/getDormFeedback", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getDormFeedback() {
        return dormServiceImpl.getDormFeedback();
    }

}
