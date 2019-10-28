package com.ccwl.manager.service;

import com.ccwl.manager.dao.AdminDao;
import com.ccwl.manager.model.User;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Service("adminServiceImpl")
public class AdminServiceImpl implements UserService {

    @Autowired
    HttpSession session;

    @Resource(name = "adminDao")
    private AdminDao adminDao;

    private static final String path =
            "C:\\Users\\yingfeng\\IdeaProjects\\spring\\ccwl-banana-manager\\web\\uploadFile\\%s";

    public String AccountLogin(String number, String password) {
        User user = adminDao.getAccountByNum(number, password);
        if (user == null){
            return "{\"state\": \"fail\", \"msg\": \"请检查账号密码输入是否正确！\"}";
        }else{
            this.session.setAttribute("USER", "admin");
            return "{\"state\": \"success\", \"msg\": " + JSONObject.fromObject(user) + "}";
        }
    }

    public String modifyInfo(Object[] msg) {
        Object who = this.session.getAttribute("USER");
        new User();
        int result = this.adminDao.modifyInfo(msg, who.toString());
        if (result == -1) {
            return "{\"state\": \"success\",\"msg\": \"找不到用户\"}";
        } else {
            return "{\"state\": \"success\"}";
        }
    }

    public String remove() {
        this.session.removeAttribute("USER");
        return "{\"msg\": \"success\"}";
    }


    public String uploadExcel(CommonsMultipartFile file) {
        try {
            String fileName = String.format(path, file.getOriginalFilename());
            String Extensions = fileName.substring(fileName.length()-4);  // 获取后缀名，判断是否Excel文件
            if ("xlsx".equals(Extensions)) {
                File newFile=new File(fileName);
                file.transferTo(newFile);
                return "{\"state\": \"success\"}";
            }else if (".xls".equals(Extensions)){
                File newFile=new File(fileName);
                file.transferTo(newFile);
                return "{\"state\": \"success\"}";
            }else {
                return "{\"state\": \"fail\", \"msg\":\"File format fail!\"}";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"state\": \"fail\"}";
        }
    }


}
