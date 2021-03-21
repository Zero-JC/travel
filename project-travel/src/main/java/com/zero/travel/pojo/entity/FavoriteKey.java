package com.zero.travel.pojo.entity;

import lombok.Data;

/**
 * @author LJC
 */
@Data
public class FavoriteKey {
    private Integer userId;

    private Integer routeId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }
}