package com.gdky.graduation.wxApi;

import com.gdky.graduation.dao.RatingsDao;
import com.gdky.graduation.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/4 0004.
 */
@RestController
@RequestMapping("wx/api/ratings")
public class RatingsApi {
    @Resource
    private RatingsDao ratingsDao;

    @GetMapping("")
    public Result getRatings() {
        List<Map<String,Object>> ratinsList = ratingsDao.getRatings();
        return Result.genSuccessResult(ratinsList);
    }
}
