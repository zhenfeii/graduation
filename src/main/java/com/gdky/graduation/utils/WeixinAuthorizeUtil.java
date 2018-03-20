package com.gdky.graduation.utils;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/3/19 0019.
 */
public class WeixinAuthorizeUtil {
    private static final String APPID = "wxf7f7bd216a1d35c3";
    private static final String SECRET = "f4ee5f6a704d1b305636e8cf270bdb17";
    /**
     * 通过code换取网页授权access_token
     *
     * 获取code后，请求以下链接获取access_token：
     * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     *
     */
    public static String getAccessTokenBycode(String code) {
        //拼接url
        StringBuffer url = new StringBuffer("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
        url.append(APPID);
        url.append("&secret=");
        url.append(SECRET);
        url.append("&grant_type=authorization_code&code=");
        url.append(code);

        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(1000);
        simpleClientHttpRequestFactory.setReadTimeout(1000);
        RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory);
        String resultString =  restTemplate.getForObject(url.toString(),String.class);

        return null;
    }
}
