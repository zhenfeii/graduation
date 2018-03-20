package com.gdky.graduation.thirdApi;

import com.gdky.graduation.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  心知天气 api
 * Created by Administrator on 2018/3/15 0015.
 */
@RequestMapping("/thirdApi/xinzhi")
public class WeatherApi {

    @GetMapping("/nowWeather")
    public Result getNowWeather(){
        return null;
    }



}
