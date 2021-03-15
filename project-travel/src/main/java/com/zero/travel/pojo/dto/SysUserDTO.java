package com.zero.travel.pojo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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

    @NotBlank(message = "用户姓名不能为空")
    private String name;

    @NotBlank(message = "登录账户不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6,max = 20,message = "密码长度限制(6~20)")
    private String password;

    @Pattern(regexp = "^[A-Za-z0-9]+([_\\.][A-Za-z0-9]+)*@([A-Za-z0-9\\-]+\\.)+[A-Za-z]{2,6}$",message = "邮箱格式不合法")
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
