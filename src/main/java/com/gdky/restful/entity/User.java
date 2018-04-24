package com.gdky.restful.entity;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
public class User {

    /**
     *  商家老板ID
     * ID_.
     */
    private Integer id;

    /**
     * 商家ID.
     */
    private Integer seller_id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     *  用户名
     */
    private String userName;

    /**
     * 手机号码.
     */
    private String phone;

    /**
     * 密码.
     */
    private String password;

    /**
     * 头像.
     */
    private String avatar;

    /**
     * 登录信息
     */
    private String dlxx;


    public User(User u) {
        this.id = u.id;
        this.seller_id = u.seller_id;
        this.loginName = u.loginName;
        this.userName = u.userName;
        this.phone = u.phone;
        this.password = u.password;
        this.avatar = u.avatar;
        this.dlxx = u.dlxx;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Integer seller_id) {
        this.seller_id = seller_id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return avatar;
    }

    public void setPhoto(String photo) {
        this.avatar = photo;
    }

    public String getDlxx() {
        return dlxx;
    }

    public void setDlxx(String dlxx) {
        this.dlxx = dlxx;
    }
}
