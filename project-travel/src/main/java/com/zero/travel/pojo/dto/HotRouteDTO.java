package com.zero.travel.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 热门旅游线路 数据传输对象
 * @author LJC
 * @version 1.0
 * @date 2021/3/21 16:30
 */
@Data
public class HotRouteDTO implements Serializable {

    /**
     * 线路信息
     */
    private Integer routeId;

    private String routeName;

    private Double price;

    private String routeIntroduce;

    private String imageUrl;

    private String strategy;

    /**
     * 商家信息
     */
    private Integer sellerId;

    private String sellerName;

    private String phone;

    private String address;

    /**
     * 收藏次数
     */
    private Integer count;

}
