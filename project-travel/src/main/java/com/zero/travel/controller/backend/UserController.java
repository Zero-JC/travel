package com.zero.travel.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.travel.common.enums.SystemConstant;
import com.zero.travel.controller.CommonController;
import com.zero.travel.pojo.dto.UserDTO;
import com.zero.travel.pojo.entity.User;
import com.zero.travel.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
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
    public String findAll(Integer pageNum,ModelMap modelMap){
        if (ObjectUtils.isEmpty(pageNum)){
            pageNum = SystemConstant.PAGE_NUM;
        }
        Integer pageSize = SystemConstant.PAGE_SIZE;

        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);

        UserDTO userDTO = new UserDTO();
        modelMap.addAttribute("pageInfo",pageInfo);
        modelMap.addAttribute("userDTO",userDTO);
        return "backend/userManager";
    }

    /**
     * 条件搜索
     * @param pageNum
     * @param userDTO
     * @param modelMap
     * @return
     */
    @RequestMapping("/search")
    public String search(Integer pageNum,UserDTO userDTO,ModelMap modelMap){
        if (ObjectUtils.isEmpty(pageNum)){
            pageNum = SystemConstant.PAGE_NUM;
        }
        Integer pageSize = SystemConstant.PAGE_SIZE;

        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userService.findByParam(userDTO);
        PageInfo<User> pageInfo = new PageInfo<>(userList);

        modelMap.addAttribute("pageInfo",pageInfo);
        modelMap.addAttribute("userDTO",userDTO);

        return "backend/userManager";
    }
}
