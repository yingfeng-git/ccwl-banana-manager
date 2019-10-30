package com.ccwl.manager.dao;

import com.ccwl.manager.model.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDao extends BaseDao{
    private RowMapper<Student> rm = new RowMapper<Student>() {
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student s1 = new Student();
            s1.setId(rs.getInt("id"));
            s1.setNumber(rs.getString("number"));
            s1.setName(rs.getString("name"));
            s1.setSex(rs.getString("sex"));
            s1.setNationality(rs.getString("nationality"));
            s1.setNativePlace(rs.getString("native_place"));
            s1.setCollege(rs.getString("college"));
            s1.setPoliticsStatus(rs.getString("politics_status"));
            s1.setPhoneNumber(rs.getString("phone_number"));
            s1.setName(rs.getString("name"));
            s1.setProfessional(rs.getString("professional"));
            s1.setClassName(rs.getString("class_name"));
            s1.setPermission("student");
            return s1;
        }
    };

    public Student getAccountByNum(String number, String password) {
        String sql = String.format("select * from %s where number = ? and password = ? limit 1", "student");
        Object[] params = new Object[] {number, password};


        List<Student> studentList = jdbcTemplate.query(sql, params, rm);
        return studentList.isEmpty() ? null : studentList.get(0);
    }

    public Student getAccountInfoByNum(String number) {
        String sql = String.format("select * from %s where number = ? limit 1", "student");
        Object[] params = new Object[] {number};
        List<Student> studentList = jdbcTemplate.query(sql, params, rm);
        return studentList.isEmpty() ? null : studentList.get(0);
    }

    public int modifyPassword(String number, String password, String who) {
        try {
            String sql = String.format(
                    "update %s SET password=? WHERE number=?", who);
            return jdbcTemplate.update(sql, password, number);

        }catch (Exception e){
            return -1;
        }
    }
}
