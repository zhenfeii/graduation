package com.gdky.graduation.Config;

import com.gdky.graduation.security.EntryPointUnauthorizedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Administrator on 2018/3/20 0020.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                // All of Spring Security will ignore the requests
                .antMatchers("/resources/**").antMatchers(HttpMethod.POST, "/");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .headers().cacheControl().disable().and()
                .servletApi().and()

                .authorizeRequests()
                //身份验证api允许匿名访问
                .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()

                //微信api允许匿名访问
                .antMatchers("/wx/api/**").permitAll()

                // /thirdApi 允许匿名访问
                .antMatchers("/thirdApi/**").permitAll()

                //pc端api需要验证
                .antMatchers("/pc/api/**").authenticated().and();

    }

}
