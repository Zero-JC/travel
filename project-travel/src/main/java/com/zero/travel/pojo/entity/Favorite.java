package com.zero.travel.pojo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author LJC
 */
@Data
public class Favorite {

    private Integer userId;

    private Integer routeId;

    private Date time;

}