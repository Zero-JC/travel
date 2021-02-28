package com.zero.travel.controller.backend;

import com.sun.javafx.fxml.expression.KeyPath;
import com.zero.travel.pojo.dto.SysUserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/2/28 19:00
 */
@Controller
@RequestMapping("/backend/sysUser")
public class SysUserController {

    @RequestMapping("login")
    public String login(SysUserDTO sysUserDTO){
        //TODO:登录验证
        System.out.println(sysUserDTO);
        return "/backend/main";
    }

    @RequestMapping("test")
    public String test(ModelMap map){
        //TODO:登录验证
        System.out.println("测试···");
        List<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("Jack");
        list.add("Alice");
        list.add("Bob");

        map.addAttribute("lists",list);

        return "/backend/Test";
    }
}
