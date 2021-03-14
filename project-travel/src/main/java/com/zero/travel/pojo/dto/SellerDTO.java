package com.zero.travel.pojo.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 商家数据对象
 * @author LJC
 * @version 1.0
 * @date 2021/3/12 19:22
 */
@Data
public class SellerDTO implements Serializable {

    @Min(value = 0,message = "商家编号格式不正确")
    private Integer sellerId;

    @NotBlank(message = "商家名称不能为空")
    private String sellerName;

    @NotBlank(message = "联系电话不能为空")
    @Pattern(regexp = "^[0-9]{4,16}$",message = "联系电话格式不正确(4~16位数字字符)")
    private String phone;

    @NotBlank(message = "商家地址不能为空")
    private String address;
}
