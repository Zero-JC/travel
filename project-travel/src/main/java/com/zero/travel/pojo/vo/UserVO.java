package com.zero.travel.pojo.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 前台用户
 * @author LJC
 * @version 1.0
 * @date 2021/3/20 14:05
 */
@Data
public class UserVO implements Serializable {

    private Integer userId;

    @NotBlank(message = "姓名不能为空")
    @Length(min=1,max=30,message = "姓名长度超限")
    private String name;

    @NotBlank(message = "用户名不能为空")
    @Length(min=1,max=30,message = "用户名长度超限")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 3,max = 20,message = "密码长度限制(3~20)")
    private String password;

    @Email(message = "邮箱格式不合法")
    private String email;

    private Integer status;


}
