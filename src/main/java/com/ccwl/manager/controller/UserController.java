package com.ccwl.manager.controller;

import com.ccwl.manager.service.AdminServiceImpl;
import com.ccwl.manager.service.StudentServiceImpl;
import com.ccwl.manager.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/account")
@CrossOrigin
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
    public String login(@RequestParam(value="number") String number,
                        @RequestParam(value="password") String password,
                        @RequestParam(value="permission") char permission) {
        switch (permission){
            case '0' : return adminServiceImpl.AccountLogin(number, password);  // admin
            case '1' : return teacherServiceImpl.AccountLogin(number, password); // teacher
        }
        return studentServiceImpl.AccountLogin(number, password);  //student
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String logout() {
        return studentServiceImpl.remove();
    }

    @RequestMapping(value="/getUserMsg", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getUserMsg(@RequestParam(value="number") String number) {
        try{
            String who = this.session.getAttribute("USER").toString();
            if ("student".equals(who)){
                return studentServiceImpl.AccountInfo(number);
            }else {
                return teacherServiceImpl.AccountInfo(number);
            }
        } catch (NullPointerException e){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }

    }

    @RequestMapping(value="/modifyInfo", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String ModifyInfo(@RequestParam(value="number") String number,
                             @RequestParam(value="nationality") String nationality,
                             @RequestParam(value="nativePlace") String nativePlace,
                             @RequestParam(value="politicsStatus") String politicsStatus,
                             @RequestParam(value="phoneNumber") String phoneNumber) {

        return adminServiceImpl.modifyInfo(new Object[] {
                nationality,
                nativePlace,
                politicsStatus,
                phoneNumber,
                number
        });
    }

    @RequestMapping(value="/modifyPassword", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String modifyPassword(@RequestParam(value="number") String number,
                                 @RequestParam(value="password") String password) {
        String who = this.session.getAttribute("USER").toString();
        if ("student".equals(who)){
            return studentServiceImpl.modifyPassword(number, password);
        }else {
            return teacherServiceImpl.modifyPassword(number, password);
        }
    }

}