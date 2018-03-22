package com.gdky.graduation.wx.service.impl;

import com.gdky.graduation.wx.dao.SellerDao;
import com.gdky.graduation.wx.service.SellerService;
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
public class SellerServiceImpl implements SellerService {
    @Resource
    private SellerDao sellerDao;

    @Override
    public List<Map<String, Object>> getSellerList(Map<String,Object> map) {
        return sellerDao.getSellerList(map);
    }

    @Override
    public Map<String,Object> getSellerListById(Integer sellerId) {
        return sellerDao.getSellerListById(sellerId);
    }

    @Override
    public List<Map<String, Object>> getImgs() {
        return sellerDao.getImgs();
    }


}
