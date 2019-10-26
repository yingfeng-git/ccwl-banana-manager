package com.ccwl.manager.dao;

import com.ccwl.manager.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminDao extends BaseDao{
    public User getAccountByNum(String number, String password) {
        String sql = "select * from %s where number = ? and password = ? limit 1";
        Object[] params = new Object[] {number, password};
        RowMapper<User> rm = new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User u1 = new User();
                u1.setNumber(rs.getString("number"));
                u1.setPermission("admin");
                return u1;
            }
        };

        List<User> userList = jdbcTemplate.query(String.format(sql, "admin"), params, rm);
        return userList.isEmpty()? null : userList.get(0);
    }

    public static void main(String[] args){
        AdminDao u = new AdminDao();
        System.out.println(u.getAccountByNum("admin01", "123456"));
    }
}
