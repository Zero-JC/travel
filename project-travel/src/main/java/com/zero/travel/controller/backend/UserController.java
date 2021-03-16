package com.zero.travel.controller.backend;

import com.zero.travel.controller.CommonController;
import com.zero.travel.pojo.entity.User;
import com.zero.travel.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 客户管理
 * @author LJC
 * @version 1.0
 * @date 2021/3/16 17:52
 */
@Controller
@RequestMapping("/backend/user")
public class UserController extends CommonController {

    @Resource
    private UserService userService;

    /**
     * 查询所有
     * @param modelMap
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(ModelMap modelMap){
        List<User> userList = userService.findAll();

        modelMap.addAttribute("userList",userList);
        return "backend/userManager";
    }
}
