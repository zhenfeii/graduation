package com.gdky.graduation.pc.pcService;

import com.gdky.graduation.entity.CustomUserDetails;

/**
 * Created by Administrator on 2018/3/20 0020.
 */
public interface CustomUserService {
    CustomUserDetails loadUserByUsername(String username);
}
