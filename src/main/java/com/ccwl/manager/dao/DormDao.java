package com.ccwl.manager.dao;


import com.ccwl.manager.model.Dorm;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Repository
public class DormDao extends BaseDao{
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private RowMapper<Dorm> rm = new RowMapper<Dorm>() {
        public Dorm mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dorm d1 = new Dorm();
            d1.setClassName(rs.getString("class_name"));
            d1.setDormNumber(rs.getString("dorm_number"));
            d1.setNumber(rs.getString("number"));
            d1.setName(rs.getString("name"));
            return d1;
        }
    };

    public Dorm getDormByNum(String number) {
        String sql = "select s.class_name, d.* from dorm d, student s where d.number = s.number and d.number = ?";
        Object[] params = new Object[] {number};

        List<Dorm> studentList = jdbcTemplate.query(sql, params, rm);
        return studentList.isEmpty() ? null : studentList.get(0);
    }

    public List<Dorm> getDormByClassName(String className) {
        String sql =
                "select class_name, d.* from dorm d left join student s on s.number = d.number where s.class_name = ?;";
        Object[] params = new Object[] {className};
        List<Dorm> dormList = jdbcTemplate.query(sql, params, rm);
        return dormList.isEmpty()? null : dormList;
    }

    public List<Map<String, Object>> getClassName(){
        String sql = "select DISTINCT class_name from student";
        List<Map<String, Object>> dormList = jdbcTemplate.queryForList(sql);
        return dormList.isEmpty()? null : dormList;
    }

    public int insertVisitorMsg(String number, String msg){
        String sql = "insert into visitor_message(number, message, create_time) values(?, ?, ?)";
        Object[] params = new Object[]{number, msg, df.format(new Date())};
        int result = jdbcTemplate.update(sql, params);
        return result;
    }

    public int insertDormFeedback(String number, String msg){
        String sql = "insert into dorm_feedback(dorm_number, message, create_time) values(?, ?, ?)";
        Object[] params = new Object[]{number, msg, df.format(new Date())};
        int result = jdbcTemplate.update(sql, params);
        return result;
    }

    public List<Map<String, Object>> getVisitorMsg() {
        String sql =
                "select * from visitor_message order by create_time desc;";
        List<Map<String, Object>> visitorMsgs = jdbcTemplate.queryForList(sql);
        return visitorMsgs.isEmpty()? null : visitorMsgs;
    }


    public List<Map<String, Object>> getDormFeedback() {
        String sql =
                "select * from dorm_feedback order by create_time desc;";
        List<Map<String, Object>> dormFeedbacks = jdbcTemplate.queryForList(sql);
        return dormFeedbacks.isEmpty()? null : dormFeedbacks;
    }

}
