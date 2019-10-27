package com.ccwl.manager.service;


import com.ccwl.manager.dao.DormDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service("dormServiceImpl")
public class DormServiceImpl {

    @Autowired
    HttpSession session;

    @Resource(name="dormDao")
    DormDao dormDao;

    public String getDormByNum(String number){
        try{
            this.session.getAttribute("USER").toString();
            return String.format("{\"state\": \"success\", \"msg\": %s}",
                    JSONObject.fromObject(dormDao.getDormByNum(number)).toString());
        }catch (NullPointerException e){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }

    public String getDormByClassName(String ClassName){
        try{
            this.session.getAttribute("USER").toString();
            return String.format("{\"state\": \"success\", \"msg\": %s}",
                    JSONArray.fromObject(dormDao.getDormByClassName(ClassName)).toString());
        }catch (NullPointerException e){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }

    public String getClassName(){
        try{
            this.session.getAttribute("USER").toString();
            return String.format("{\"state\": \"success\", \"msg\": %s}",
                    JSONArray.fromObject(dormDao.getClassName()));
        }catch (NullPointerException e){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }

    public String setVisitorMsg(String number, String msg){
        try {
            this.session.getAttribute("USER").toString();
            int result = dormDao.insertVisitorMsg(number, msg);
            if (result == 1){
                return "{\"state\": \"success\"}";
            }else{
                return "{\"state\": \"fail\"}";
            }
        }catch (NullPointerException e){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }

    }

    public String setDormFeedback(String number, String msg){
        try{
            this.session.getAttribute("USER").toString();
            int result = dormDao.insertDormFeedback(number, msg);
            if (result == 1){
                return "{\"state\": \"success\"}";
            }else{
                return "{\"state\": \"fail\"}";
            }
        }catch (NullPointerException e){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }

    }

    public String getVisitorMsg() {
        try{
            this.session.getAttribute("USER").toString();
            return String.format("{\"state\": \"success\", \"msg\": %s}",
                    JSONArray.fromObject(dormDao.getVisitorMsg()));

        }catch (NullPointerException e){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }

    public String getDormFeedback() {
        try{
            this.session.getAttribute("USER").toString();
            return String.format("{\"state\": \"success\", \"msg\": %s}",
                    JSONArray.fromObject(dormDao.getDormFeedback()));
        }catch (NullPointerException e){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }
}
