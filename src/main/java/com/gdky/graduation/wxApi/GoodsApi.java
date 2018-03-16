package com.gdky.graduation.wxApi;

import com.gdky.graduation.domain.Result;
import com.gdky.graduation.service.GoodsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Goods API
 * Created by Administrator on 2018/2/27 0027.
 */
@RestController
@RequestMapping("/wx/api/goods")
public class GoodsApi {
    @Resource
    private GoodsService goodsService;

    @RequestMapping("/{sellerId}")
    public Result getGoods(@PathVariable Integer sellerId) {
        List<Map<String, Object>> goodsList = goodsService.getGoods(sellerId);
        return Result.genSuccessResult(goodsList);
    }

}
