package com.ccwl.manager.dao;

import com.ccwl.manager.model.Teacher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
                t1.setPermission("teacher");
                return t1;
            }
        };
        List<Teacher> teacherList = jdbcTemplate.query(sql, params, rm);
        return teacherList.isEmpty() ? null : teacherList.get(0);
    }

    public Teacher getAccountInfoByNum(String number) {
        String sql = String.format("select * from %s where number = ? limit 1", "teacher");
        Object[] params = new Object[] {number};
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
                t1.setPermission("teacher");
                return t1;
            }
        };
        List<Teacher> teacherList = jdbcTemplate.query(sql, params, rm);
        return teacherList.isEmpty() ? null : teacherList.get(0);
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

    public List<Map<String, Object>> getScoreFromClassAndCourse(String className, String course){
        String sql = "select number, name, grade_point, course from gradepoint where course = ? and number in (select student.number from student where class_name = ?)";
        Object[] param = new Object[]{
                course, className
        };
        return jdbcTemplate.queryForList(sql, param);
    }

    public List<Map<String, Object>> getAttenceFromClassAndCourse(String className, String course){
        String sql = "select s.class_name, a.* FROM attence a left join student s on a.number = s.number where a.course = ? and s.class_name = ?;";
        Object[] param = new Object[]{
                course, className
        };
        return jdbcTemplate.queryForList(sql, param);
    }

    public List<Map<String, Object>> getAttenceFromNumber(String number){
        String sql = "select s.class_name, a.* FROM attence a left join student s on a.number = s.number where s.number = ?;";
        return jdbcTemplate.queryForList(sql, number);
    }

    public static void main(String[] args) {
        TeacherDao t = new TeacherDao();
        System.out.println(JSONArray.fromObject(t.getAttenceFromNumber("s101")));
    }
}
