package com.zero.travel.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户登录的传输对象
 * @author LJC
 * @version 1.0
 * @date 2021/2/28 19:04
 */
@Data
public class SysUserDTO implements Serializable {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotNull(message = "验证码不能为空")
    private Integer code;

}
