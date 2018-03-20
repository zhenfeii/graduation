package com.gdky.graduation.controller;

import com.gdky.graduation.domain.Result;
import com.gdky.graduation.entity.AuthRequest;
import com.gdky.graduation.entity.CustomUserDetails;
import com.gdky.graduation.pcService.CustomUserService;
import com.gdky.graduation.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 身份认证接口
 * Created by Administrator on 2018/3/20 0020.
 */
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private CustomUserService customUserService;

    @PostMapping("/api/auth/login")
    public Result login(@RequestBody AuthRequest authRequest){
        //进行验证
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //自己去找数据库查找
        CustomUserDetails customUserDetails = customUserService.loadUserByUsername(authRequest.getUsername());
        String random = getRandomString(10);
        //生成token
        String token = tokenUtils.generateToken(customUserDetails,random);

        //返回token
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        return Result.genSuccessResult(token);
    }


    //length表示生成字符串的长度
    public String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}