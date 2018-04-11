package com.gdky.restful.entity;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
public class Role {

    /**
     * ID_.
     */
    private Integer id_;

    /**
     * 角色名称.
     */
    private String roleName;

    /**
     * 描述.
     */
    private String ms;

    public Integer getId_() {
        return id_;
    }

    public void setId_(Integer id_) {
        this.id_ = id_;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }
}
