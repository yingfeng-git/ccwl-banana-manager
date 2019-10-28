package com.ccwl.manager.controller;


import com.ccwl.manager.service.AdminServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Resource(name = "adminServiceImpl")
    AdminServiceImpl adminServiceImpl;

    @RequestMapping(value="/uploadExcel")
    @ResponseBody
    public String uploadExcel(@RequestParam("file") CommonsMultipartFile file){
        return adminServiceImpl.uploadExcel(file);
    }
}
