package com.gdky.graduation.pc.pcApi;

import com.gdky.graduation.domain.Result;
import com.gdky.graduation.wx.service.PcGoodsService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/18 0018.
 */
@RestController
public class PcImgApi {
    @Resource
    private PcGoodsService pcGoodsService;

    @PutMapping("/pc/api/img")
    public Result alertImg(@RequestBody Map<String,Object> map) throws IOException {
        //把数据库foods的images更改
        pcGoodsService.alertImgById((Integer)map.get("foodsId"),(String)map.get("imgUrl"));
        return Result.genSuccessResult();
    }
}
