package com.gdky.graduation.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
public interface GoodsService {
    public List<Map<String,Object>> getGoods(Integer sellerId);
}
