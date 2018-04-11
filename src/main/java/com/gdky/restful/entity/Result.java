package com.gdky.restful.entity;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

/**
 * 统一API响应结果封装
 */
public class Result {
    private int code;
    private String message;
    private Object data;

    public static Result genSuccessResult() {
        return new Result()
                .setCode(200)
                .setMessage("success");
    }

    public static Result genSuccessResult(Object data) {
        return new Result()
                .setCode(200)
                .setMessage("success")
                .setData(data);
    }

    public static Result error(int code, String message) {
        return new Result()
                .setCode(code)
                .setMessage(message)
                .setData(null);
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
