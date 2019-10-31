package com.ccwl.manager.dao;

import com.ccwl.manager.model.SchoolCard;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class SchoolCardDao extends BaseDao{
    public SchoolCard getInfoByNum(String number) {
        String sql = String.format("select * from %s where number = ? limit 1", "yikatong");
        Object[] params = new Object[] {number};
        RowMapper<SchoolCard> rm = new RowMapper<SchoolCard>() {
            public SchoolCard mapRow(ResultSet rs, int rowNum) throws SQLException {
                SchoolCard sc1 = new SchoolCard();
                sc1.setNumber(rs.getString("number"));
                sc1.setHotWaterBalance(rs.getInt("hot_water_balance"));
                sc1.setMealCardBalance(rs.getInt("meal_card_balance"));
                return sc1;
            }
        };

        List<SchoolCard> schoolCardList = jdbcTemplate.query(sql, params, rm);
        return schoolCardList.isEmpty()? null : schoolCardList.get(0);
    }


    public int setSchoolCardMoney(String number, int money) {
        try {
            String sql = String.format(
                    "update %s SET meal_card_balance = meal_card_balance + ? WHERE number=?", "yikatong");
            return jdbcTemplate.update(sql, money, number);
        }catch (Exception e){
            return -1;
        }
    }

    public int setSchoolCardHotWaterMoney(String number, int money) {
        try {
            String sql = String.format(
                    "update %s SET hot_water_balance = hot_water_balance + ? WHERE number=?", "yikatong");
            return jdbcTemplate.update(sql, money, number);
        }catch (Exception e){
            return -1;
        }
    }

    public static void main(String[] args) {
        SchoolCardDao ss = new SchoolCardDao();
        ss.setSchoolCardHotWaterMoney("s101", 20);
    }

}
