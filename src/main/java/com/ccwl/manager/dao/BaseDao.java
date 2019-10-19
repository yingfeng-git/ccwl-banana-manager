package com.ccwl.manager.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDao{
    private static final ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    static final JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");

}
