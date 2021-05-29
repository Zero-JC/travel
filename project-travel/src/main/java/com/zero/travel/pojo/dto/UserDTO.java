package com.zero.travel.pojo.dto;

import lombok.Data;

/**
 * 客户数据传输对象
 * @author LJC
 * @version 1.0
 * @date 2021/3/16 22:34
 */
@Data
public class UserDTO {

    private Integer userId;

    private String name;

    private String username;

    private String password;

    private String email;

    private Integer status;

}
