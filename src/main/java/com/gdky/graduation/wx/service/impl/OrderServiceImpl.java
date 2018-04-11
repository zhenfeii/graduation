package com.gdky.graduation.wx.service.impl;

import com.gdky.graduation.wx.dao.OrderDao;
import com.gdky.graduation.wx.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/12 0012.
 */

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Override
    public void addOrder(Map<String, Object> params) {
        orderDao.addOrder(params);
    }

    @Override
    public List<Map<String, Object>> getOrderBySellerId(Integer sellerId) {
        List<Map<String, Object>> orderList = orderDao.getOrderBySellerId(sellerId);
        return orderList;
    }

    @Override
    public void deleteOrderById(Integer orderId) {
        orderDao.deleteOrderById(orderId);
    }

    @Override
    public List<Map<String, Object>> getOrderFoodByUuid(String uuid) {
        List<Map<String, Object>> foodsList = orderDao.getOrderFoodByUuid(uuid);
        return foodsList;
    }

    @Override
    public void editOrder(Map<String, Object> map) {
        orderDao.editOrder(map);
    }

    @Override
    public List<Map<String, Object>> getOrderByCondition(Map<String, Object> params) {
        List<Map<String, Object>> orderList = orderDao.getOrderByCondition(params);
        return orderList;
    }

    @Override
    public List<Map<String, Object>> getOrderByopenId(String opendId) {
        return orderDao.getOrderByOpenId(opendId);
    }

    @Override
    public void deleteOrderByUuid(Map<String, Object> map) {
        orderDao.deleteOrderByUuid(map);
    }
}
