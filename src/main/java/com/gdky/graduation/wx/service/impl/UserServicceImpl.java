package com.gdky.graduation.wx.service.impl;

import com.gdky.graduation.wx.dao.UserDao;
import com.gdky.graduation.wx.service.UserServicce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Administrator on 2018/3/28 0028.
 */
@Service
@Transactional
public class UserServicceImpl implements UserServicce {
    @Autowired
    private UserDao userDao;

    @Override
    public void alertAddress(Map<String, Object> map) {
        userDao.alertAddress(map);
    }

    @Override
    public String checkUser(String openid) {
        return userDao.checkUser(openid);
    }

    @Override
    public void addAddress(Map<String, Object> map) {
        userDao.addAddress(map);
    }
}
