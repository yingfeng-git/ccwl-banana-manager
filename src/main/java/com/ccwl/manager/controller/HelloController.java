package com.ccwl.manager.controller;


import com.ccwl.manager.dao.userDao;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;

@RestController
@RequestMapping("/mvc")
public class HelloController {

    @Resource(name = "userDao")
    private userDao userDao;

    @RequestMapping(value="user1")
    @ResponseBody
    public String getUser1(@RequestParam(value = "name") String name){
        return "you put a string: " + name;
    }

    @RequestMapping(value="/loginbypost", method= RequestMethod.POST)
    public String loginByPost(@RequestParam(value="username") String name,
                            @RequestParam(value="password") String pwd) {

        return String.format("{\"name\": \"%s\", \"password\": \"%s\"}", name, pwd);
    }

    @RequestMapping(value="/loginbypostfrommysql", method= RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String loginByPostFromMysql(@RequestParam(value="num") String num,
                              @RequestParam(value="password") String password) {

        return userDao.getAccountByNum(num, password).toString();
    }


}
