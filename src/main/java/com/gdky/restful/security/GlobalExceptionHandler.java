package com.gdky.restful.security;

import com.gdky.restful.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public Result defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("", e);
        String message = e.getMessage();
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            String url = ((org.springframework.web.servlet.NoHandlerFoundException) e).getRequestURL();
            return Result.error(404, "访问路径:" + url + " 不存在！");
        } else {
            return Result.error(501, message);
        }

    }
}
