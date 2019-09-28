package com.ccwl.manager.dao;

import com.ccwl.manager.model.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Repository
public class userDao {
    private static final ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    private static final JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");

    private static final String ACCOUNT_BY_NUM = String.format("select * from %s where numb = ? and password = ? limit 1", "t_ccwl_user");

    public Account getAccountByNum(String numb, String password) {
        Object[] objects = new Object[]{numb, password};
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(ACCOUNT_BY_NUM, objects);
            return wrap(result);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    private static Account wrap(Map<String, Object> objectMap){
        Account account = new Account();
        account.setId((Integer) objectMap.get("id"));
        account.setAge((Integer) objectMap.get("age"));
        account.setNum((String) objectMap.get("numb"));
        account.setName((String) objectMap.get("name"));
        account.setPassword((String) objectMap.get("password"));
        account.setPermission((String) objectMap.get("permission"));

        return account;
    }

    public static void main(String[] args){
        userDao u = new userDao();
        System.out.println(u.getAccountByNum("17B543155", "123456"));
    }

}
