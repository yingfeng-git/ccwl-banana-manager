package com.ccwl.manager.service;

import com.ccwl.manager.dao.SchoolCardDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service("schoolCardServiceImpl")
public class SchoolCardServiceImpl {

    @Autowired
    HttpSession session;

    @Resource(name="schoolCardDao")
    SchoolCardDao schoolCardDao;

    public String getSchoolCardInfo(String number){
        try{
            this.session.getAttribute("USER").toString();
            return String.format("{\"state\": \"success\", \"msg\": %s}",
                    JSONObject.fromObject(schoolCardDao.getInfoByNum(number)).toString());
        }catch (NullPointerException e){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }

    public String setSchoolCardMoney(String number, int money){
        int result = schoolCardDao.setSchoolCardMoney(number, money);
        if (result == -1) {
            return "{\"state\": \"success\",\"msg\": \"找不到用户\"}";
        } else {
            return "{\"state\": \"success\"}";
        }
    }

    public String setSchoolCardHotWaterMoney(String number, int money){
        int result = schoolCardDao.setSchoolCardHotWaterMoney(number, money);
        if (result == -1) {
            return "{\"state\": \"success\",\"msg\": \"找不到用户\"}";
        } else {
            return "{\"state\": \"success\"}";
        }
    }
}
