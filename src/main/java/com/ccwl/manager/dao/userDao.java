package com.ccwl.manager.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class userDao {
    private static final ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    private static final JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");

    private static final String NUM_2_MSG = String.format("select * from %s where num = ?", "t_ccwl_user");

    public List<Map<String, Object>> num2msg(String num) throws SQLException {
        return jdbcTemplate.queryForList(NUM_2_MSG, new Object[]{num});
    }

    public static void main(String[] args) throws SQLException{
        userDao u = new userDao();
        System.out.println(u.num2msg("17B543153"));
    }
}
