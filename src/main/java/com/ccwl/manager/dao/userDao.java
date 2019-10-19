package com.ccwl.manager.dao;

import com.ccwl.manager.model.Account;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class userDao extends BaseDao{
    public Account getAccountByNum(String numb, String password) {
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

    private static Account wrap(Map<String, Object> objectMap, String permission){
        Account account = new Account();
        account.setId((Integer) objectMap.get("id"));
        account.setNumber((String) objectMap.get("number"));
        account.setName((String) objectMap.get("name"));
        account.setSex((String) objectMap.get("sex"));
        account.setAge((Integer) objectMap.get("age"));
        account.setPhonenumber((String) objectMap.get("phone_number"));
        account.setPermission(permission);
        return account;
    }

    public static void main(String[] args){
        userDao u = new userDao();
        System.out.println(u.getAccountByNum("s101", "123"));
    }
}
