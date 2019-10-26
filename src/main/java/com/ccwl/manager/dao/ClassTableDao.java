package com.ccwl.manager.dao;

import com.ccwl.manager.model.Course;
import com.ccwl.manager.model.Student;
import com.ccwl.manager.model.Teacher;
import com.ccwl.manager.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClassTableDao extends BaseDao {

    //返回对象数组
    public List<Course> getCourseByClassId(String number,String table) {
        String sql =String.format( "SELECT student.name ,teacher.name,class_time,classroom,course " +
                "FROM curriculum,student,teacher,student_class,teacher_class WHERE " +
                "student.number = student_class.number AND " +
                "teacher.number = teacher_class.number AND " +
                "curriculum.class_id = student_class.class_id AND " +
                "curriculum.class_id = teacher_class.class_id AND " +
                "%s.number = ? ",table);
        Object[] params = new Object[] {number};
        RowMapper<Course> rm = new RowMapper<Course>() {
            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
                Course c1 = new Course();
                c1.setStudent_name(rs.getString("student.name"));
                c1.setTeacher_name(rs.getString("teacher.name"));
                c1.setClass_time(rs.getString("class_time"));
                c1.setClassroom(rs.getString("classroom"));
                c1.setCourse(rs.getString("course"));

                return c1;
            }
        };

        List<Course> courseList = jdbcTemplate.query(sql, params, rm);
        return courseList.isEmpty() ? null : courseList;
    }

    public static void main(String[] args){
        ClassTableDao classTableDao = new ClassTableDao();
        System.out.println(classTableDao.getCourseByClassId("s101","student"));
    }
}
