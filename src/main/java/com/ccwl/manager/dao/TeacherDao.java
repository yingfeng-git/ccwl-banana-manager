package com.ccwl.manager.dao;

import com.ccwl.manager.model.Teacher;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TeacherDao extends BaseDao{
    public Teacher getAccountByNum(String number, String password) {
        String sql = String.format("select * from %s where number = ? and password = ? limit 1", "teacher");
        Object[] params = new Object[] {number, password};
        RowMapper<Teacher> rm = new RowMapper<Teacher>() {
            public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
                Teacher t1 = new Teacher();
                t1.setId(rs.getInt("id"));
                t1.setNumber(rs.getString("number"));
                t1.setName(rs.getString("name"));
                t1.setSex(rs.getString("sex"));
                t1.setNationality(rs.getString("nationality"));
                t1.setNativePlace(rs.getString("native_place"));
                t1.setCollege(rs.getString("college"));
                t1.setPoliticsStatus(rs.getString("politics_status"));
                t1.setPhoneNumber(rs.getString("phone_number"));
                t1.setName(rs.getString("name"));
                return t1;
            }
        };
        List<Teacher> teacherList = jdbcTemplate.query(sql, params, rm);
        return teacherList.isEmpty() ? null : teacherList.get(0);
    }

    public static void main(String[] args){
        TeacherDao t = new TeacherDao();
        System.out.println(t.getAccountByNum("t101", "456"));
    }
}
