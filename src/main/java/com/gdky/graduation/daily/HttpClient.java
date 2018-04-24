package com.gdky.graduation.daily;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * 学习httpClient
 * <p>
 * Created by Administrator on 2018/4/19 0019.
 */
public class HttpClient {

    /**
     * 发送 get请求
     */
    public static void get(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httpget.
        HttpGet httpget = new HttpGet(url);
        // 执行get请求.
        try {
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity httpEntity = response.getEntity();
            Long length = httpEntity.getContentLength();
//            System.out.println(""httpget.getURI());
//            System.out.println(EntityUtils.toString(httpEntity));
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            String url = "https://blog.csdn.net/weixin_39454194/article/details/79560111";
            HttpClient.get(url);
            System.out.println("/**\n" +
                    "*\n" +
                    "*\n" +
                    "*\n" +
                    "*\n" +
                    "*\n" +
                    "*\n" +
                    "正在刷csdn阅读量；每10分钟访问一次" +
                    "*\n" +
                    "*\n" +
                    "*\n" +
                    "*\n" +
                    "*\n" +
                    "*\n" +
                    "*\n" +
                    "**/");
            //每10分钟访问一次
            Thread.currentThread().sleep(1000 * 60 * 10);

        }
    }
}
