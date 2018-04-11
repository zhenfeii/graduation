package com.gdky.restful.entity;

import java.util.Date;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
public class User {

    /**
     * ID_.
     */
    private Integer id_;

    /**
     * 登陆名称.
     */
    private String loginName;

    /**
     * 用户名称.
     */
    private String userName;

    /**
     * 手机号码.
     */
    private String phone;

    /**
     * 入职时间.
     */
    private Date rzsj;

    /**
     * 职位.
     */
    private String zw;

    /**
     * 密码.
     */
    private String pwd;

    /**
     * 头像.
     */
    private String photo;

    /**
     * 所在科室.
     */
    private Integer deptid;

    /**
     * 生日.
     */
    private Date birthday;

    private String dlxx;

    private Integer accountEnabled;
    private Integer accountExpired;
    private Integer accountLocked;
    private Integer credentialsExpired;

    public User(User u) {
        this.id_ = u.id_;
        this.loginName = u.loginName;
        this.userName = u.userName;
        this.phone = u.phone;
        this.rzsj = u.rzsj;
        this.zw = u.zw;
        this.pwd = u.pwd;
        this.photo = u.photo;
        this.deptid = u.deptid;
        this.birthday = u.birthday;
        this.dlxx = u.dlxx;
        this.accountEnabled = u.accountEnabled;
        this.accountExpired = u.accountExpired;
        this.accountLocked = u.accountLocked;
        this.credentialsExpired = u.credentialsExpired;
    }

    public User() {
    }

    public Integer getId_() {
        return id_;
    }

    public void setId_(Integer id_) {
        this.id_ = id_;
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

    public Date getRzsj() {
        return rzsj;
    }

    public void setRzsj(Date rzsj) {
        this.rzsj = rzsj;
    }

    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDlxx() {
        return dlxx;
    }

    public void setDlxx(String dlxx) {
        this.dlxx = dlxx;
    }

    public Integer getAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(Integer accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public Integer getAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(Integer accountExpired) {
        this.accountExpired = accountExpired;
    }

    public Integer getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Integer accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Integer getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(Integer credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }
}
