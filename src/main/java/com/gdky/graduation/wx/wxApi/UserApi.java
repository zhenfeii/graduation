package com.gdky.graduation.wx.wxApi;

import com.gdky.graduation.domain.Result;
import com.gdky.graduation.wx.service.UserServicce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 买家信息
 * Created by Administrator on 2018/3/28 0028.
 */
@RestController
@RequestMapping("/wx/api/user")
public class UserApi {
    @Autowired
    private UserServicce userServicce;

    /**
     * 修改买家地址
     * @param map
     * @return
     */
    @PutMapping("/address")
    public Result alertAddress(@RequestBody Map<String,Object> map){
        userServicce.alertAddress(map);
        return Result.genSuccessResult();
    }

    /**
     * 检查数据表user_address有没有这个用户的地址
     * @param openid
     * @return
     */
    @GetMapping("/checkUser")
    public Result checkUser(@RequestParam String openid){
       String booleanString = userServicce.checkUser(openid);
       Map<String,String> map = new HashMap<>();
       map.put("userAlreadyExist",booleanString);
       return Result.genSuccessResult(map);
    }

    @PostMapping("/address")
    public Result addAddress(@RequestBody Map<String,Object> map){
        userServicce.addAddress(map);
        return Result.genSuccessResult();
    }
}
