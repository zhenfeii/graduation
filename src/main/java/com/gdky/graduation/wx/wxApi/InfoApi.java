package com.gdky.graduation.wx.wxApi;

import com.gdky.graduation.domain.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/26 0026.
 */
@RestController
@RequestMapping("/apiInfo")
public class InfoApi {

    @RequestMapping("")
    public Result info(){
        Map<String,Object> map = new HashMap<>();
        map.put("Version","1.5.10.RELEASE");
        map.put("api function","2018届黄振飞毕业设计后台接口");
        map.put("后台框架","spring boot");
        map.put("dao技术", "JdbcTemplate");

        return Result.genSuccessResult(map);
    }
}
