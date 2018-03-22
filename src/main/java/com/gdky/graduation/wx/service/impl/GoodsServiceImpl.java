package com.gdky.graduation.wx.service.impl;

import com.gdky.graduation.wx.dao.GoodsDao;
import com.gdky.graduation.wx.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {
    @Resource
    GoodsDao goodsDao;

    @Override
    public List<Map<String, Object>> getGoods(Integer sellerId) {
        List<Map<String, Object>> goodsList = goodsDao.getGoods(sellerId);
        return goodsList;
    }
}
