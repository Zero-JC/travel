package com.zero.travel.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/2/27 8:18
 */
@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping("msg")
    public @ResponseBody String test01(){

        return "Hello Travel";
    }
}
