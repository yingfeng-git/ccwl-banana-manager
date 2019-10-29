package com.ccwl.manager.service;


import com.ccwl.manager.dao.RecordDao;
import com.ccwl.manager.model.Record;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service("recordServiceImpl")
public class RecordServiceImpl {
    @Autowired
    HttpSession session;

    @Resource(name = "recordDao")
    private RecordDao recordDao;
    public String RecordSelect(String number) {
        List<Record> recordList = recordDao.getRecordByNumber(number);
        if (recordList == null){
            return "{\"state\": \"fail\", \"msg\": \"请检查账号是否正确！\"}";
        }else{

            return "{\"state\": \"success\", \"msg\": " + JSONArray.fromObject( recordList) + "}";
        }
    }

}
