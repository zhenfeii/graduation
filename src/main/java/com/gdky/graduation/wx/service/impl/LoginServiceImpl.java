package com.gdky.graduation.wx.service.impl;

import com.gdky.graduation.wx.dao.UserDao;
import com.gdky.graduation.wx.service.LoginService;
import com.gdky.graduation.utils.Openid;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/12 0012.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserDao userDao;

    @Override
    public Map<String,Object> login(Map<String, Object> params) {
        Map<String,Object> map = new HashMap<>();
        // 根据小程序传来的code来获取session_key 和 openid
        String openId = Openid.getOpenidByScore((String) params.get("code"));

        map.put("openid",openId);
        params.put("openid",openId);

        //查询这个openId在数据表user存不存在
        List<String> openIdList = userDao.getopenIdList();

        /**
         * 设置一个标杆,
         * false代表没有这个用户,
         * true代表用户已存在
         */
        boolean flag = false;
        for (int i = 0; i < openIdList.size(); i++) {
            if(openIdList.get(i).equals(openId)){
                flag = true;
                break;
            }
        }
        //用户不存在，則新增这个用户
        if(flag == false){
            userDao.addUser(params);
        }
        return map;
    }

}
