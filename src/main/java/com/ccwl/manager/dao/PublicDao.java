package com.ccwl.manager.dao;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PublicDao extends BaseDao{
    public List<Map<String,Object>> getCourse(){
        String sql = "select distinct course from curriculum";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String,Object>> getCollege(){
        String sql = "select distinct college from student;";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String,Object>> getTeacher(){
        String sql = "select college, name from teacher;";
        return jdbcTemplate.queryForList(sql);
    }

    public static void main(String[] args) {
        PublicDao p = new PublicDao();
        System.out.println(JSONArray.fromObject(p.getCourse()));
        System.out.println(JSONArray.fromObject(p.getCollege()));
        System.out.println(JSONArray.fromObject(p.getTeacher()));
    }
}
