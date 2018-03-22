package com.gdky.graduation.wx.dao.impl;

import com.gdky.graduation.wx.dao.GoodsDao;
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
 * Created by Administrator on 2018/2/27 0027.
 */
@Repository
public class GoodsDaoImpl implements GoodsDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getGoods(Integer sellerId) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        jdbcTemplate.query("select * from goods WHERE sellerId=?", new Object[]{sellerId},new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Map<String, Object> goodsMap = new HashMap<>();
                goodsMap.put("name", rs.getString("name"));
                goodsMap.put("type", rs.getString("type"));

                String foodsListSql = "SELECT * FROM foods WHERE foods.goodId=? ";
                List<Map<String, Object>> foodsList = jdbcTemplate.queryForList(foodsListSql, new Object[]{rs.getLong("id")});
                goodsMap.put("foods",foodsList);

                resultList.add(goodsMap);
            }
        });
        return resultList;
    }
}
