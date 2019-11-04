package com.ccwl.manager.dao;

import com.ccwl.manager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public int insertScore(List<ArrayList<Object>> data) {
        int count = 0;
        String sql = "insert into gradepoint(number, name, grade_point, class_id, course) " +
                "values(?, ?, ?, (select curriculum.class_id from curriculum where course=? limit 1), ?)";
        for (ArrayList<Object> arrayList : data) {
            try {
                count += jdbcTemplate.update(
                        sql, arrayList.get(0), arrayList.get(1), arrayList.get(2), arrayList.get(3), arrayList.get(3));
            }catch (Exception e){
                ;
            }
        }
        return count;
    }

    public int insertTeacher(String number, String college, String sex, String name){
        String chack = "select count(*) from teacher where number=?";
        if ((Long)jdbcTemplate.queryForMap(chack, number).get("count(*)") > 0){
            return -1;
        }else {
            String sql = "insert into teacher(number, name, sex, college, password) values(?, ?, ?, ?, '123456')";
            return jdbcTemplate.update(sql, number, name, sex, college);
        }
    }

    public int insertStudent(String number, String college, String professional, String className, String sex, String name) {
        String chack = "select count(*) from student where number=?";
        if ((Long)jdbcTemplate.queryForMap(chack, number).get("count(*)") > 0){
            return -1;
        }else {
            String sql = "insert into student(number, name, sex, professional, class_name, college, password) " +
                    "values(?, ?, ?, ?, ?, ?, '123456')";
            return jdbcTemplate.update(sql, number, name, sex, professional, className, college);
        }

    }

    public int insertCourse(String course) {
        String sql = "insert into course(course) values(?)";
        return jdbcTemplate.update(sql, course);


    }

    public int uploadAttence(List<ArrayList<Object>> data){
        int count = 0;
        String sql =
                "insert into attence(number, student_name, class_id, course, teacher_name, classroom, which_week, week, class_time, attence) " +
                "values(?,?,(select class_id from curriculum where course = ? limit 1),?,?,?,?,?,?,?) ";

        for (ArrayList<Object> arrayList : data) {
            try {
                count += jdbcTemplate.update(
                        sql, arrayList.get(0), arrayList.get(1), arrayList.get(2), arrayList.get(2), arrayList.get(3),
                        arrayList.get(4),arrayList.get(5),arrayList.get(6),arrayList.get(7),arrayList.get(8));
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return count;
    }



    public static void main(String[] args) {
        AdminDao adminDao = new AdminDao();
//        adminDao.insertCourse("华为工程师认证");
//        System.out.println(adminDao.getStudents());
    }

}
