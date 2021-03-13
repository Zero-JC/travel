package com.zero.travel.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * 全局异常处理
 * @author LJC
 * @version 1.0
 * @date 2021/3/12 9:11
 */
//@ControllerAdvice
public class ErrorController extends CommonController{

    @ExceptionHandler(Exception.class)
    public String error(Exception e, Model model){

        model.addAttribute("error",e.toString());
        model.addAttribute("message",e.getMessage());
        model.addAttribute("status",500);
        model.addAttribute("timestamp",new Date());
        log.error(e.getMessage());

        return "error/error";
    }
}
