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
public class AdminDao extends BaseDao{

    @Autowired
    HttpSession session;

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

    public int modifyInfo(Object[] msg, String who){
        try {
            String sql = String.format(
                    "update %s SET nationality=?, native_place=?, politics_status=?, phone_number=? WHERE number=?",
                    who);
            return jdbcTemplate.update(sql, msg);

        }catch (Exception e){
            return -1;
        }
    }

    public static void main(String[] args) {
        AdminDao a = new AdminDao();
        a.modifyInfo(new Object[]{
                "大概的定个蛋糕",
                "撒旦法",
                "s堆叠地方",
                "但是",
                "s101"
        }, "student");
    }
}
