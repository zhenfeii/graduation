package com.gdky.graduation.pc.pcApi;

import com.gdky.graduation.domain.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/29 0029.
 */
@RestController
@RequestMapping("/pc/api/info")
public class PcApiInfo {

    @RequestMapping("")
    public Result pcInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("version", "1.5.10.RELEASE");
        map.put("apiFunction", "2018届黄振飞毕业设计pc端后台接口");
        map.put("framework", "Spring Boot");
        map.put("daoLayerTechnology", "JdbcTemplate模板");

        return Result.genSuccessResult(map);
    }
}
