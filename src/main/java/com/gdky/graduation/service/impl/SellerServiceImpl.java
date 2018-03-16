package com.gdky.graduation.service.impl;

import com.gdky.graduation.dao.SellerDao;
import com.gdky.graduation.service.SellerService;
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
    public List<Map<String, Object>> getSellerList() {
        return sellerDao.getSellerList();
    }

    @Override
    public Map<String,Object> getSellerListById(Integer sellerId) {
        return sellerDao.getSellerListById(sellerId);
    }


}
