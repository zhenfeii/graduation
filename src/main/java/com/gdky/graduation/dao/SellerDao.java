package com.gdky.graduation.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
public interface SellerDao {
    public List<Map<String, Object>> getSellerList();

    Map<String,Object> getSellerListById(Integer sellerId);
}
