package com.zero.travel.pojo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
public class LoginDTO implements Serializable {

    @NotBlank(message = "用户名不能为空")
    @Length(min=1,max=30,message = "用户名长度超限")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min=1,max=30,message = "密码长度超限")
    private String password;

    @NotBlank(message = "验证码不能为空")
    private String code;

}
