package com.gdky.restful.security;

import com.gdky.restful.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    private TokenUtils tokenUtils = new TokenUtils();

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader("token");
        String username = this.tokenUtils.getUsernameFromToken(token);

        //SecurityContextHolder.getContext().getAuthentication() 该方法是做什么？
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

            //验证token 是否有效
            if (this.tokenUtils.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}
