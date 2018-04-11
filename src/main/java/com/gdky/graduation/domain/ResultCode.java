package com.gdky.graduation.domain;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public enum ResultCode {
    //成功
    SUCCESS(200),
    //失败
    FAIL(400),
    //未认证（签名错误）
    UNAUTHORIZED(401),
    //接口不存在
    NOT_FOUND(404),
    //服务器内部错误
    INTERNAL_SERVER_ERROR(500),
    //服务错误
    SERVICE_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}