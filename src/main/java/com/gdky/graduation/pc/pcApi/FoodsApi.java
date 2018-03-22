package com.gdky.graduation.pc.pcApi;

import com.gdky.graduation.domain.Result;
import com.gdky.graduation.wx.service.PcGoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/16 0016.
 */
@RestController
@RequestMapping("/pc/api")
public class FoodsApi {
    @Resource
    PcGoodsService pcGoodsService;

    /**
     * 商家获取自己的商品详情
     * @return
     * @param
     */
    @GetMapping("/foods")
    public Result getFoodsBySellerId(@RequestParam Integer sellerId){
        List<Map<String,Object>> goodsList  =  pcGoodsService.getGoods(sellerId);
        return Result.genSuccessResult(goodsList);
    }

    /**
     * 删除特定的商品,不要乱删，导入数据很麻烦
     */
    @DeleteMapping("/foods")
    public Result deleteSingleFoods(@RequestBody Map<String,Object> map){
        pcGoodsService.deleteSingleFoods(map);
        return Result.genSuccessResult();
    }
}
