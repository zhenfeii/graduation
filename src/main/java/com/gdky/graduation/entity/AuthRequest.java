package com.gdky.graduation.entity;

import java.io.Serializable;

/**
 * Serializable: 序列化接口，不知道具体用途
 * Created by Administrator on 2018/3/20 0020.
 */
public class AuthRequest implements Serializable{
    private String username;
    private String password;

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
}
