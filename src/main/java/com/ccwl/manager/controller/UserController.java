package com.ccwl.manager.controller;

import com.ccwl.manager.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

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

    @RequestMapping(value="/modifyPersonalInfo", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String ModifyPersonalInfo(@RequestParam(value="nationality") String nationality,
                                     @RequestParam(value="nativePlace") String nativePlace,
                                     @RequestParam(value="politicsStatus") String politicsStatus,
                                     @RequestParam(value="phoneNumber") String phoneNumber,
                                     @RequestParam(value="contactEmergency") String contactEmergency,
                                     @RequestParam(value="contactPhoneNumber") String contactPhoneNumber) {
        System.out.println(nationality);
        System.out.println(nativePlace);
        System.out.println(politicsStatus);
        System.out.println(phoneNumber);
        System.out.println(contactEmergency);
        System.out.println(contactPhoneNumber);
        return "success";
    }

    @RequestMapping(value="/modifyPersonalInfoFromJson", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String ModifyPersonalInfo(@RequestParam(value="msg") String msg) {
        System.out.println(msg);
        JSONObject j1 = JSONObject.fromObject(msg);
        
        return "success";
    }

}
