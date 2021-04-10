package com.zero.travel.common.enums;

import java.io.Serializable;

/**
 * 系统常量
 * @author LJC
 * @version 1.0
 * @date 2021/3/14 12:07
 */
public class SystemConstant implements Serializable {

    /**
     * 初始显示第一页数据
     */
    public static final Integer PAGE_NUM = 1;

    /**
     * 默认每页显示5条数据
     */
    public static final Integer PAGE_SIZE = 5;

    /**
     * 默认每页显示8条数 前台系统
     */
    public static final Integer PAGE_SIZE_FRONT = 9;


    public static final String SYS_USER = "sysUser";
    public static final Integer ROLE_SYS_USER = 1;

    public static final String USER = "user";
    public static final Integer ROLE_USER = 2;

    public static final String SELLER = "seller";
    public static final Integer ROLE_SELLER = 3;

    public static final String ROUTE = "route";

}
