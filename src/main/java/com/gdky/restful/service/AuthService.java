package com.gdky.restful.service;

import com.gdky.restful.dao.AuthDao;
import com.gdky.restful.entity.Role;
import com.gdky.restful.entity.User;
import com.gdky.restful.security.CustomUserDetails;
import com.gdky.restful.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
@Service
public class AuthService {

    @Resource
    private AuthDao authDao;

    private TokenUtils tokenUtils = new TokenUtils();


    public User getUser(String username) {
        return authDao.getUser(username);
    }

    public List<Role> getRolesByUser(String userName) {
        return authDao.getRolesByUser(userName);
    }

    public void insertDlxx(String random, CustomUserDetails userDetails) {
        authDao.insertDlxx(random, userDetails);
    }

    public User getSellerUserInfo(String token) {
        String loginName = tokenUtils.getUsernameFromToken(token);
        return authDao.getUser(loginName);
    }
}
