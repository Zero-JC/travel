package com.zero.travel.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 线路搜索
 * @author LJC
 * @version 1.0
 * @date 2021/3/20 21:55
 */
@Data
public class RouteSearchQuery implements Serializable {

    /**
     * 线路名称
     */
    private String routeName;


    private Double minPrice;

    private Double maxPrice;


    /**
     * 所属商家
     */
    private Integer sellerId;


}
