package com.gdky.graduation.pcService.impl;

import com.gdky.graduation.PcDao.CustomUserDao;
import com.gdky.graduation.entity.CustomUserDetails;
import com.gdky.graduation.pcService.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018/3/20 0020.
 */
@Service
@Transactional
public class CustomUserServiceImpl implements CustomUserService{
    @Autowired
    private CustomUserDao customUserDao;

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        return customUserDao.loadUserByUsername(username);
    }
}
