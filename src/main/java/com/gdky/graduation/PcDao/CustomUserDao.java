package com.gdky.graduation.PcDao;

import com.gdky.graduation.entity.CustomUserDetails;

/**
 * Created by Administrator on 2018/3/20 0020.
 */
public interface CustomUserDao {
    CustomUserDetails loadUserByUsername(String username);
}
