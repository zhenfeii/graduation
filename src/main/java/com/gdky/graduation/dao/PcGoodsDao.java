package com.gdky.graduation.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/16 0016.
 */
public interface PcGoodsDao {
    List<Map<String,Object>> getGoods(Integer sellerId);

    void deleteSingleFoods(Map<String, Object> map);

    void alertImgById(Integer foodsId, String imgUrl);
}
