package com.zero.travel.pojo.dto;

import lombok.Data;

/**
 * 商家数据对象
 * @author LJC
 * @version 1.0
 * @date 2021/3/12 19:22
 */
@Data
public class SellerDTO {

    private Integer sellerId;

    private String sellerName;

    private String phone;

    private String address;
}
