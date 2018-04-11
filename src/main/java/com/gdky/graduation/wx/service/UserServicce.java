package com.gdky.graduation.wx.service;

import java.util.Map;

/**
 * Created by Administrator on 2018/3/28 0028.
 */
public interface UserServicce {
    void alertAddress(Map<String, Object> map);

    String checkUser(String openid);

    void addAddress(Map<String, Object> map);
}
