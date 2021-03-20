package com.zero.travel.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Route implements Serializable {
    private Integer routeId;

    private String routeName;

    private Double price;

    private String routeIntroduce;

    private String imageUrl;

    private String strategy;

    private Integer sellerId;

    private Date createTime;

    private Date updateTime;


}