package com.gdky.graduation.wx.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/12 0012.
 */
public interface OrderDao {
    void addOrder(Map<String, Object> params);

    List<Map<String,Object>> getOrderBySellerId(Integer sellerId);

    void deleteOrderById(Integer orderId);

    List<Map<String,Object>> getOrderFoodByUuid(String uuid);

    void editOrder(Map<String, Object> map);

    List<Map<String,Object>> getOrderByCondition(Map<String, Object> params);

    List<Map<String,Object>> getOrderByOpenId(String opendId);

    void deleteOrderByUuid(Map<String, Object> map);
}
