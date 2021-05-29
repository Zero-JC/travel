package com.zero.travel.pojo.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 17250
 */
@Data
@ToString
public class User implements Serializable {
    private Integer userId;

    private String name;

    private String username;

    private String password;

    private String email;

    private Integer status;

    ///private String code;

    private Date createTime;

    private Date updateTime;


}