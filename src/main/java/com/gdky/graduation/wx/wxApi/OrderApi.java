package com.gdky.graduation.wx.wxApi;

import com.gdky.graduation.domain.Result;
import com.gdky.graduation.wx.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/12 0012.
 */
@RestController
@RequestMapping("/wx/api/order")
public class OrderApi {
    @Resource
    private OrderService orderService;

    /**
     * 增加一条新订单
     * @param params
     * @return
     */
    @PostMapping("")
    public Result addOrder(@RequestBody Map<String,Object> params){
        orderService.addOrder(params);
        return Result.genSuccessResult();
    }

    /**
     * 根据openId 来获取个人订单
     */
    @GetMapping("")
    public Result getOrderByopenId(@RequestParam String openId){
        List<Map<String,Object>> orderList =  orderService.getOrderByopenId(openId);
        return Result.genSuccessResult(orderList);
    }

    @DeleteMapping("")
    public Result deleteOrderByUuid(@RequestBody Map<String,Object> map){
        orderService.deleteOrderByUuid(map);
        return Result.genSuccessResult();
    }
}
