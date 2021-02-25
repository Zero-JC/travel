package com.zero.travel.pojo.dto;

import java.io.Serializable;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/2/25 12:09
 */
public class User implements Serializable {

    private String username;

    private Integer age;

    public User() {
    }

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
