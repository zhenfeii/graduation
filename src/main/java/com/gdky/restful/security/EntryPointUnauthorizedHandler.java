package com.gdky.restful.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdky.restful.entity.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
    private Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        logger.info("401 " + e);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String message = "用户信息过期或失效,请重新登录！";
        if (e.getMessage().equals("账户或密码错误！")) {
            message = e.getMessage();
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), Result.error(401, message));
    }
}
