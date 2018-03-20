package com.gdky.graduation.controller;

import com.gdky.graduation.utils.WeixinAuthorizeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信授权登录
 * Created by Administrator on 2018/3/19 0019.
 */
@RestController
public class weixinAuthorize {

    @RequestMapping("/weixin/auth")
    public String graduation(@RequestParam("code") String code) {
        String accessToken =  WeixinAuthorizeUtil.getAccessTokenBycode(code);
        return "get code success: " + code;
    }
}
