package com.zero.travel.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 前台用户
 * @author LJC
 * @version 1.0
 * @date 2021/3/20 14:05
 */
@Data
public class UserVO {

    private Integer userId;

    private String name;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String email;

    private Integer status;

    private String code;

}
