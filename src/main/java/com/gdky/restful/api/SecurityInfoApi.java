package com.gdky.restful.api;

import com.gdky.restful.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/9 0009.
 */
@RestController
public class SecurityInfoApi {

    @GetMapping("/api/info")
    public Result Info(){
        Map<String,Object> map = new HashMap<>();
        map.put("function","security");
        return Result.genSuccessResult(map);
    }

    @GetMapping("/api/hello")
    public Result hello(){
        return Result.genSuccessResult("hello security");
    }
}
