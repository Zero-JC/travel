package com.zero.travel.pojo.entity;

import java.util.Date;

/**
 * @author LJC
 */
public class Favorite extends FavoriteKey {
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}