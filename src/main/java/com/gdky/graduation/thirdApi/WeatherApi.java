package com.gdky.graduation.thirdApi;

import com.gdky.graduation.domain.Result;
import org.json.JSONObject;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 *  心知天气 api
 * Created by Administrator on 2018/3/15 0015.
 */
@RestController
@RequestMapping("/thirdApi/xinzhi")
public class WeatherApi {

    /**
     * 获取实时天气
     * key = kcpopbac8eiw06n3   可在心知天气个人找到
     */
    @GetMapping("/nowWeather")
    public Result getNowWeather(@RequestParam String longitudeAndLatitude){
        String url = "https://api.seniverse.com/v3/weather/now.json?" +
                "key=kcpopbac8eiw06n3&language=zh-Hans&unit=c&location="
                + longitudeAndLatitude;

        //使用restTemplate优雅地访问rest服务
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(1000);
        simpleClientHttpRequestFactory.setReadTimeout(1000);
        RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory);
        //把结果直接装在一个字符串
        String result = restTemplate.getForObject(url.toString(),String.class);

        JSONObject jsonObject = new JSONObject(result);
        JSONObject nowWeatherJsonObject = jsonObject.optJSONArray("results").getJSONObject(0).getJSONObject("now");
        String text = nowWeatherJsonObject.opt("text").toString();
        String code = nowWeatherJsonObject.opt("code").toString();
        String temperature = nowWeatherJsonObject.opt("temperature").toString();

        Map<String,String> map = new HashMap<>();
        map.put("text",text);
        map.put("code",code);
        map.put("temperature",temperature);
        return Result.genSuccessResult(map);
    }

    /**
     * 测试 getNowWeather() 方法
     * @param args
     */
    public static void main(String[] args) {
        WeatherApi weatherApi = new WeatherApi();
        weatherApi.getNowWeather("22.93772:113.38424");
    }

}
