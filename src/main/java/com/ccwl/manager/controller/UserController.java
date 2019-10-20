package com.ccwl.manager.controller;

import com.ccwl.manager.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/account")
public class UserController {
    @Autowired
    HttpSession session;

    @Resource(name = "UserServiceImpl")
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value="/login", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String loginByPostFromMysql(@RequestParam(value="numb") String numb,
                                       @RequestParam(value="password") String password) {
        return userServiceImpl.AccountLogin(numb, password);
    }



    @RequestMapping(value="/logout", method = RequestMethod.GET, produces="text/html;charset=UTF-8")
    @ResponseBody
    public String logout() {
        return userServiceImpl.remove();
    }


    @RequestMapping(value="/getUserMsg", method = RequestMethod.GET, produces="text/html;charset=UTF-8")
    @ResponseBody
    public String getSessionTest() {
        try {
            return this.session.getAttribute("USER").toString();
        }catch(NullPointerException e){
            return "{\"msg\": \"用户未登陆！\"}";
        }
    }


}
