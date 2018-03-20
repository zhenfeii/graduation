package com.gdky.graduation.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by Administrator on 2018/3/20 0020.
 */
public class CustomUserDetails {
    private String username;
    private String password;
    private String dlxx;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    private Collection<? extends GrantedAuthority> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDlxx() {
        return dlxx;
    }

    public void setDlxx(String dlxx) {
        this.dlxx = dlxx;
    }
}
