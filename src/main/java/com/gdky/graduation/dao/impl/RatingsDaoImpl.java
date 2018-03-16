package com.gdky.graduation.dao.impl;

import com.gdky.graduation.dao.RatingsDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/4 0004.
 */
@Repository
public class RatingsDaoImpl implements RatingsDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getRatings() {
        List<Map<String, Object>> ratingsList = new ArrayList<>();
        String sql = "SELECT * FROM ratings";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Map<String,Object> map = new HashMap<>();
                map.put("username",rs.getString("USERNAME"));
                map.put("ratetime",rs.getString("RATETIME"));
                map.put("delivertype",rs.getString("DELIVERYTIME"));
                map.put("score",rs.getString("SCORE"));
                map.put("ratetype",rs.getString("RATETYPE"));
                map.put("text",rs.getString("TEXT"));
                map.put("avatar",rs.getString("AVATAR"));

                String recommendSql = "SELECT RECOMMEND FROM ratingsrecommend WHERE RATINS_ID=?";
                List<Object> recommendList = new ArrayList<>();
                jdbcTemplate.query(recommendSql,new Object[]{rs.getInt("id")},new RowCallbackHandler() {
                            @Override
                            public void processRow(ResultSet rs) throws SQLException {
                                recommendList.add(rs.getString("RECOMMEND"));
                            }
                        });
                map.put("recommend",recommendList);
                ratingsList.add(map);
            }
        });

        return ratingsList;
    }
}
