package com.zero.travel.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * 系统用户-数据传输对象
 * @author LJC
 * @version 1.0
 * @date 2021/3/14 16:58
 */
@Data
public class SysUserDTO {

    private Integer sysId;

    private String name;

    private String username;

    ///private String password;

    private String email;

    private Integer isActive;

    private Integer roleId;

    private Date createTime;

    ///private Date updateTime;


    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
