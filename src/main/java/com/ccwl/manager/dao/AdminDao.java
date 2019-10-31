package com.ccwl.manager.dao;

import com.ccwl.manager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminDao extends BaseDao {

    @Autowired
    HttpSession session;

    public User getAccountByNum(String number, String password) {
        String sql = "select * from %s where number = ? and password = ? limit 1";
        Object[] params = new Object[]{number, password};
        RowMapper<User> rm = new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User u1 = new User();
                u1.setNumber(rs.getString("number"));
                u1.setPermission("admin");
                return u1;
            }
        };

        List<User> userList = jdbcTemplate.query(String.format(sql, "admin"), params, rm);
        return userList.isEmpty() ? null : userList.get(0);
    }

    public int modifyInfo(Object[] msg, String who) {
        try {
            String sql = String.format(
                    "update %s SET nationality=?, native_place=?, politics_status=?, phone_number=? WHERE number=?",
                    who);
            return jdbcTemplate.update(sql, msg);

        } catch (Exception e) {
            return -1;
        }
    }

    public int resetPassword(String who, String number) {
        try {
            String sql = "update %s set password=123456 where number=?";
            return jdbcTemplate.update(String.format(sql, who), number);
        } catch (Exception e) {
            return -1;
        }
    }

    public int modifyStudentInfo(String number, String college, String professional, String className, String sex, String name){
        try {
            String sql = "update student set college=?, professional=?, class_name=?, sex=?, name=? where number=?";
            return jdbcTemplate.update(sql, college, professional, className, sex, name, number);
        } catch (Exception e) {
            return -1;
        }
    }

    public int modifyTeacherInfo(String number, String college, String sex, String name){
        try {
            String sql = "update teacher set college=?, sex=?, name=? where number=?";
            return jdbcTemplate.update(sql, college, sex, name, number);
        } catch (Exception e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        AdminDao adminDao = new AdminDao();
        adminDao.modifyStudentInfo("s102", "college", "professional",
                "className", "sex", "name");
    }
}
