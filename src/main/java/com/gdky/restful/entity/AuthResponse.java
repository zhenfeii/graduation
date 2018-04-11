package com.gdky.restful.entity;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
public class AuthResponse {

    private String token;
    private String tokenHash;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(String token) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        // MD5不加盐hash
        String last = token.substring(token.length() - 1);
        String pass = encoder.encodePassword(last + token, null);
        this.tokenHash = pass;
    }
}
