package com.gdky.graduation.wx.service.impl;

import com.gdky.graduation.wx.dao.PcGoodsDao;
import com.gdky.graduation.wx.service.PcGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/16 0016.
 */
@Service
@Transactional
public class PcGoodsServiceImpl implements PcGoodsService {
    @Resource
    private PcGoodsDao pcGoodsDao;
    @Override
    public List<Map<String, Object>> getGoods(Integer sellerId) {
        List<Map<String, Object>> goodsList = pcGoodsDao.getGoods(sellerId);
        return goodsList;
    }

    @Override
    public void deleteSingleFoods(Map<String, Object> map) {
        pcGoodsDao.deleteSingleFoods(map);
    }

    @Override
    public void alertImgById(Integer foodsId, String imgUrl) {
        pcGoodsDao.alertImgById(foodsId,imgUrl);
    }
}
