package com.gdky.graduation.wx.wxApi;

import com.gdky.graduation.domain.Result;
import com.gdky.graduation.wx.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 微信登录
 * 把openId 返回给前台
 * Created by Administrator on 2018/3/12 0012.
 */
@RestController
@RequestMapping("/wx")
public class LoginApi {
    @Resource
    private LoginService loginService;

    @PostMapping("/api/login")
    public Result login(@RequestBody Map<String,Object> params){
        Map<String,Object> map  = loginService.login(params);
        return Result.genSuccessResult(map);
    }

}
