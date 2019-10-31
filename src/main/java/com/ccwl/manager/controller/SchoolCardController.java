package com.ccwl.manager.controller;

import com.ccwl.manager.service.SchoolCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/SchoolCard")
@CrossOrigin
public class SchoolCardController {

    @Autowired
    HttpSession session;

    @Resource(name = "schoolCardServiceImpl")
    private SchoolCardServiceImpl schoolCardServiceImpl;

    @RequestMapping(value="/getSchoolCardInfo", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getSchoolCardInfo(@RequestParam(value="number") String number) {
        return schoolCardServiceImpl.getSchoolCardInfo(number);  //student
    }

    @RequestMapping(value="/setSchoolCardMoney", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String setSchoolCardMoney(@RequestParam(value="number") String number,
                                     @RequestParam(value="money") int money) {
        return schoolCardServiceImpl.setSchoolCardMoney(number, money);
    }

    @RequestMapping(value="/setSchoolCardHotWaterMoney", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String setSchoolCardHotWaterMoney(@RequestParam(value="number") String number,
                                             @RequestParam(value="money") int money) {
        return schoolCardServiceImpl.setSchoolCardHotWaterMoney(number, money);
    }
}
