package com.zero.travel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * 通用的controller
 * @author LJC
 * @version 1.0
 * @date 2021/3/2 15:36
 */
@Controller
public class CommonController {
    /**
     * 统一的日志打印
     */
    public final Logger log = LoggerFactory.getLogger(getClass());
}
