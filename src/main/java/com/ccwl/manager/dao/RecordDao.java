package com.ccwl.manager.dao;

import com.ccwl.manager.model.Record;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RecordDao extends BaseDao {
    //返回对象数组
    public List<Record> getRecordByNumber(String number) {
        String sql =String.format( "select number,gradepoint.name,curriculum.course,grade_point from gradepoint,curriculum " +
                        "where number = ? and curriculum.class_id = gradepoint.class_id  GROUP BY curriculum.course,number,gradepoint.name,grade_point");
        Object[] params = new Object[] {number};
        RowMapper<Record> rm = new RowMapper<Record>() {
            public Record mapRow(ResultSet rs, int rowNum) throws SQLException {

                Record record = new Record();
                record.setNumber(rs.getString("number"));
                record.setName(rs.getString("name"));
                record.setCourse(rs.getString("course"));
                record.setRecords(rs.getString("grade_point"));

                return record;
            }
        };

        List<Record> recordList = jdbcTemplate.query(sql, params, rm);
        return recordList.isEmpty() ? null : recordList;
    }

    public static void main(String[] args){
        RecordDao recordDao = new RecordDao();
        System.out.println(recordDao.getRecordByNumber("s101"));
    }
}
