package com.gdky.graduation.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/27 0027.
 */
public interface GoodsDao {
    List<Map<String,Object>> getGoods(Integer sellerId);
}
