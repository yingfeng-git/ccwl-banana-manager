package com.ccwl.manager.service;

import com.ccwl.manager.dao.PublicDao;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service("publicServiceImpl")
public class PublicServiceImpl {
    @Autowired
    HttpSession session;

    @Resource(name="publicDao")
    PublicDao publicDao;

    public String getCourse(){
        try{
            this.session.getAttribute("USER").toString();
            return String.format("{\"state\": \"success\", \"msg\": %s}",
                    JSONArray.fromObject(publicDao.getCourse()).toString());
        } catch (NullPointerException e){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }

    public String getCollege(){
        try{
            this.session.getAttribute("USER").toString();
            return String.format("{\"state\": \"success\", \"msg\": %s}",
                    JSONArray.fromObject(publicDao.getCollege()).toString());
        } catch (NullPointerException e){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }

    public String getTeacher(){
        try{
            this.session.getAttribute("USER").toString();
            return String.format("{\"state\": \"success\", \"msg\": %s}",
                    JSONArray.fromObject(publicDao.getTeacher()).toString());
        } catch (NullPointerException e){
            return "{\"state\": \"fail\", \"msg\": \"用户未登陆\"}";
        }
    }

}
