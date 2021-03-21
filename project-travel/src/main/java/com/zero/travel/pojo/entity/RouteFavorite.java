package com.zero.travel.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RouteFavorite implements Serializable {
    private Integer routeId;

    private Integer count;

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}