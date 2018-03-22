package com.gdky.graduation.security;

import com.gdky.graduation.Config.Constants;
import com.gdky.graduation.entity.CustomUserDetails;
import com.gdky.graduation.pc.pcService.CustomUserService;
import com.gdky.graduation.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2018/3/20 0020.
 * extends UsernamePasswordAuthenticationFilter
 */
public class AuthenticationTokenFilter  {
    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private CustomUserService customUserService;

    private String tokenHeader = Constants.AUTH_HEADER_NAME;


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authToken = httpServletRequest.getHeader(tokenHeader);
        String username = tokenUtils.getUsernameFromToken(authToken);

        //根据username完成业务逻辑
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            CustomUserDetails customUserDetails = customUserService.loadUserByUsername(username);

            if(tokenUtils.validateToken(authToken,customUserDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        customUserDetails,null,customUserDetails.getAuthorities()
                );

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(
                        usernamePasswordAuthenticationToken);
            }
        }

    }
}
