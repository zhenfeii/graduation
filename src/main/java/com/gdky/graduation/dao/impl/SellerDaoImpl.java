package com.gdky.graduation.dao.impl;

import com.gdky.graduation.dao.SellerDao;
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
 * Created by Administrator on 2018/2/28 0028.
 */
@Repository
public class SellerDaoImpl implements SellerDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getSellerList() {
        List<Map<String,Object>> sellerList = new ArrayList<>();

        String sellerSql = "SELECT * FROM seller";
        jdbcTemplate.query(sellerSql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Map<String,Object> sellerMap = new HashMap<>();
                sellerMap.put("id",rs.getInt("id"));
                sellerMap.put("name",rs.getString("name"));
                sellerMap.put("description",rs.getString("description"));
                sellerMap.put("deliveryTime",rs.getString("deliveryTime"));
                sellerMap.put("score",rs.getString("score"));
                sellerMap.put("serviceScore",rs.getString("serviceScore"));
                sellerMap.put("foodScore",rs.getString("foodScore"));
                sellerMap.put("rankRate",rs.getString("rankRate"));
                sellerMap.put("minPrice",rs.getString("minPrice"));
                sellerMap.put("deliveryPrice",rs.getString("deliveryPrice"));
                sellerMap.put("ratingCount",rs.getString("ratingCount"));
                sellerMap.put("sellCount",rs.getString("sellCount"));
                sellerMap.put("bulletin",rs.getString("bulletin"));
                sellerMap.put("avatar",rs.getString("avatar"));

                String supportsSql = "SELECT type,description FROM sellersupports WHERE sellerId=?";
                List<Map<String,Object>> supportsList = jdbcTemplate.queryForList(supportsSql,new Object[]{rs.getLong("id")});
                sellerMap.put("supports",supportsList);

                String picsSql = "SELECT pics1,pics2,pics3,pics4 FROM sellerPics WHERE sellerId=?";
                List<Map<String,Object>> picsList = jdbcTemplate.queryForList(picsSql,new Object[]{rs.getLong("id")});
                sellerMap.put("pics",picsList);

                String infosSql = "SELECT info1,info2,info3,info4 FROM sellerInfo WHERE sellerId=?";
                List<Map<String,Object>> infosList = jdbcTemplate.queryForList(infosSql,new Object[]{rs.getLong("id")});
                sellerMap.put("infos",infosList);

                sellerList.add(sellerMap);
            }
        });
        return sellerList;
    }

    @Override
    public Map<String,Object> getSellerListById(Integer sellerId) {
        Map<String,Object> sellerMap = new HashMap<>();

        String sellerSql = "SELECT * FROM seller WHERE id=?";
        jdbcTemplate.query(sellerSql, new Object[]{sellerId},new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                sellerMap.put("id",rs.getInt("id"));
                sellerMap.put("name",rs.getString("name"));
                sellerMap.put("description",rs.getString("description"));
                sellerMap.put("deliveryTime",rs.getString("deliveryTime"));
                sellerMap.put("score",rs.getString("score"));
                sellerMap.put("serviceScore",rs.getString("serviceScore"));
                sellerMap.put("foodScore",rs.getString("foodScore"));
                sellerMap.put("rankRate",rs.getString("rankRate"));
                sellerMap.put("minPrice",rs.getString("minPrice"));
                sellerMap.put("deliveryPrice",rs.getString("deliveryPrice"));
                sellerMap.put("ratingCount",rs.getString("ratingCount"));
                sellerMap.put("sellCount",rs.getString("sellCount"));
                sellerMap.put("bulletin",rs.getString("bulletin"));
                sellerMap.put("avatar",rs.getString("avatar"));

                String supportsSql = "SELECT type,description FROM sellersupports WHERE sellerId=?";
                List<Map<String,Object>> supportsList = jdbcTemplate.queryForList(supportsSql,new Object[]{rs.getLong("id")});
                sellerMap.put("supports",supportsList);

                String picsSql = "SELECT pics1,pics2,pics3,pics4 FROM sellerPics WHERE sellerId=?";
                List<Map<String,Object>> picsList = jdbcTemplate.queryForList(picsSql,new Object[]{rs.getLong("id")});
                sellerMap.put("pics",picsList);

                String infosSql = "SELECT info1,info2,info3,info4 FROM sellerInfo WHERE sellerId=?";
                List<Map<String,Object>> infosList = jdbcTemplate.queryForList(infosSql,new Object[]{rs.getLong("id")});
                sellerMap.put("infos",infosList);
            }
        });
        return sellerMap;
    }


}
