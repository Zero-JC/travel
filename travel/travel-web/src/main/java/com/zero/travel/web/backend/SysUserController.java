package com.zero.travel.web.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/2/26 21:23
 */
@Controller
@RequestMapping("backend")
public class SysUserController {


    @RequestMapping("login")
    public String login(){

        return "/backend/login";
    }

}
