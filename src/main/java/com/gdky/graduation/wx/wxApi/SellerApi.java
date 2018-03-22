package com.gdky.graduation.wx.wxApi;

import com.gdky.graduation.domain.Result;
import com.gdky.graduation.wx.service.SellerService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
@RestController
@RequestMapping("wx/api/seller")
public class SellerApi {
    @Resource
    private SellerService sellerService;

    @Resource
    JdbcTemplate jdbcTemplate;

    /**
     * 获取所有的seller(商家)
     * 增加一个可以根据名字条件模糊查询
     * @return
     */
    @PostMapping("")
    public Result getSeller(@RequestBody Map<String,Object> map) {
        List<Map<String,Object>> sellerList = sellerService.getSellerList(map);
        return Result.genSuccessResult(sellerList);
    }

    /**
     * 获取指定的商家
     * @param sellerId
     * @return
     */
    @GetMapping("/{sellerId}")
    public Result getSellerById(@PathVariable Integer sellerId){
        Map<String,Object> sellerList = sellerService.getSellerListById(sellerId);
        return Result.genSuccessResult(sellerList);
    }

    /**
     * 获取所有商家的图标
     */
    @GetMapping("/imgs")
    public Result getImgs(){
        List<Map<String,Object>> imgsList = sellerService.getImgs();
        return Result.genSuccessResult(imgsList);
    }

}
