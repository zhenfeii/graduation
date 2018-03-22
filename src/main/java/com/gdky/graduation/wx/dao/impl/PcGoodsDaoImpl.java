package com.gdky.graduation.wx.dao.impl;

import com.gdky.graduation.wx.dao.PcGoodsDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/16 0016.
 */
@Repository
public class PcGoodsDaoImpl implements PcGoodsDao{
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getGoods(Integer sellerId) {
        String sql = "SELECT f.id,g.`name` typename,f.`name`,f.price,f.oldPrice,f.description,f.Count,f.sellCount,f.rating,f.info,f.icon,f.image " +
                "FROM foods f,goods g " +
                "WHERE f.sellerId =? AND f.goodId=g.id;";
        List<Map<String,Object>> gooodsList = jdbcTemplate.queryForList(sql,new Object[]{sellerId});
        return gooodsList;
    }

    @Override
    public void deleteSingleFoods(Map<String, Object> map) {
        String sql = "DELETE FROM foods WHERE sellerId=? AND id=?";
        jdbcTemplate.update(sql,new Object[]{(Integer)map.get("sellerId"),(Integer)map.get("foodsId")});
    }

    @Override
    public void alertImgById(Integer foodsId, String imgUrl) {
        String sql = "UPDATE foods SET image=? WHERE id=?";
        jdbcTemplate.update(sql,new Object[]{imgUrl,foodsId});
    }
}
