package com.gdky.graduation.wx.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/12 0012.
 */
public interface UserDao {
    List<String> getopenIdList();

    void addUser(Map<String, Object> params);

    void alertAddress(Map<String, Object> map);

    String checkUser(String openid);

    void addAddress(Map<String, Object> map);
}
