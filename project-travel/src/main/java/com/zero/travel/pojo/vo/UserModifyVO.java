package com.zero.travel.pojo.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/3/20 18:43
 */
@Data
public class UserModifyVO implements Serializable {

    @NotBlank(message = "姓名不能为空")
    @Length(min=1,max=30,message = "姓名长度超限")
    private String name;

    @NotBlank(message = "用户名不能为空")
    @Length(min=1,max=30,message = "用户名长度超限")
    private String username;

    @Email(message = "邮箱格式不合法")
    private String email;
}
