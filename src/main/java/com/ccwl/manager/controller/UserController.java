package com.ccwl.manager.controller;

import com.ccwl.manager.model.User;
import com.ccwl.manager.service.AdminServiceImpl;
import com.ccwl.manager.service.StudentServiceImpl;
import com.ccwl.manager.service.TeacherServiceImpl;
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

    @Resource(name = "adminServiceImpl")
    private AdminServiceImpl adminServiceImpl;

    @Resource(name = "teacherServiceImpl")
    private TeacherServiceImpl teacherServiceImpl;

    @Resource(name = "studentServiceImpl")
    private StudentServiceImpl studentServiceImpl;

    @RequestMapping(value="/login", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String login(@RequestParam(value="numb") String numb,
                        @RequestParam(value="password") String password,
                        @RequestParam(value="permission") char permission) {
        switch (permission){
            case '0' : return adminServiceImpl.AccountLogin(numb, password);  // admin
            case '1' : return teacherServiceImpl.AccountLogin(numb, password); // teacher
        }
        return studentServiceImpl.AccountLogin(numb, password);  //student
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET, produces="text/html;charset=UTF-8")
    @ResponseBody
    public String logout() {
        return studentServiceImpl.remove();
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

    @RequestMapping(value="/modifyInfo", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String ModifyStudentInfo(@RequestParam(value="msg") String msg) {
        System.out.println(msg);
        JSONObject j1 = JSONObject.fromObject(msg);

        return "{\"state\": \"success\", \"msg\": "+ msg +"}";
    }

}
