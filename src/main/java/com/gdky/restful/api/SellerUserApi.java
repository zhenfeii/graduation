package com.gdky.restful.api;

import com.gdky.restful.entity.Result;
import com.gdky.restful.entity.User;
import com.gdky.restful.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/4/11 0011.
 */
@RestController
@RequestMapping("/pc/api")
public class SellerUserApi {
    @Autowired
    private AuthService authService;

    @GetMapping("/user/info")
    public Result getSellerUserInfo(@RequestParam String token){
        User user =  authService.getSellerUserInfo(token);
        return Result.genSuccessResult(user);
    }
}
