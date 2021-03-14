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
}
