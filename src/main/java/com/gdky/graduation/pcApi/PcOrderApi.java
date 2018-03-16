package com.gdky.graduation.pcApi;

import com.gdky.graduation.domain.Result;
import com.gdky.graduation.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/13 0013.
 */
@RestController
@RequestMapping("/pc/api")
public class PcOrderApi {
    @Resource
    private OrderService orderService;


    /**
     * 获取特定商家的订单
     */
    @GetMapping("/order/{sellerId}")
    public Result getOrderBySellerId(@PathVariable Integer sellerId) {
        List<Map<String, Object>> orderList = orderService.getOrderBySellerId(sellerId);
        return Result.genSuccessResult(orderList);
    }

    /**
     * 通过订单号来获取订单食品详情
     */
    @GetMapping("/orderFood")
    public Result getOrderFood(@RequestParam String uuid) {
        List<Map<String, Object>> foodsList = orderService.getOrderFoodByUuid(uuid);
        return Result.genSuccessResult(foodsList);
    }

    /**
     * 删除单笔订单
     *
     * @param
     * @return
     */
    @DeleteMapping("/order")
    public Result deleteOrderById(@RequestBody Map<String, Object> map) {
        Integer orderId = (Integer) map.get("orderId");
        orderService.deleteOrderById(orderId);
        return Result.genSuccessResult();
    }

    /**
     * 修改订单，只能修改食物的数量与价格
     */
    @PutMapping("/order")
    public Result editOrder(@RequestParam String uuid, @RequestBody Map<String, Object> map) {
        map.put("uuid", uuid);
        orderService.editOrder(map);
        return Result.genSuccessResult();
    }

    /**
     * 根据条件查询订单
     */
    @PostMapping("/order")
    public Result getOrderByCondition(@RequestParam Integer sellerId, @RequestBody Map<String, Object> params) {
        params.put("sellerId", sellerId);
        List<Map<String, Object>> orderList = orderService.getOrderByCondition(params);
        return Result.genSuccessResult(orderList);
    }
}
