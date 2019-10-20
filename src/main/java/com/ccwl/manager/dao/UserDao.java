package com.ccwl.manager.dao;

import com.ccwl.manager.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao extends BaseDao{
    public User getAccountByNum(String numb, String password) {
        Object[] objects = new Object[]{numb, password};
        try {
            String ACCOUNT_BY_NUM = String.format("SELECT * from %s WHERE number = ? and password=?;", "teacher");
            Map<String, Object> result = jdbcTemplate.queryForMap(ACCOUNT_BY_NUM, objects);
            return wrap(result, "teacher");
        }catch (EmptyResultDataAccessException e){
            try {
                String ACCOUNT_BY_NUM = String.format("SELECT * from %s WHERE number = ? and password=?;", "student");
                Map<String, Object> result = jdbcTemplate.queryForMap(ACCOUNT_BY_NUM, objects);
                return wrap(result, "student");
            }catch(EmptyResultDataAccessException e1){
                return null;
            }
        }
    }

    private static User wrap(Map<String, Object> objectMap, String permission){
        User user = new User();
        user.setId((Integer) objectMap.get("id"));
        user.setNumber((String) objectMap.get("number"));
        user.setName((String) objectMap.get("name"));
        user.setSex((String) objectMap.get("sex"));
        user.setAge((Integer) objectMap.get("age"));
        user.setPhonenumber((String) objectMap.get("phone_number"));
        user.setPermission(permission);
        return user;
    }

    public static void main(String[] args){
        UserDao u = new UserDao();
        System.out.println(u.getAccountByNum("s101", "123"));
    }
}
