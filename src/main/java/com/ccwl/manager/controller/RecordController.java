package com.ccwl.manager.controller;

import com.ccwl.manager.service.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/record")
public class RecordController {
    @Autowired
    HttpSession session;


    @Resource(name = "recordServiceImpl")
    private RecordServiceImpl recordServiceImpl;


    @RequestMapping(value="/getRecordInfo", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    @ResponseBody
    public String getRecordInfo(@RequestParam(value="number") String number) {
        try {
            this.session.getAttribute("USER").toString();
            return recordServiceImpl.RecordSelect(number);
        } catch (NullPointerException e) {
            return "{\"msg\": \"用户未登陆！\"}";
        }
    }
}
