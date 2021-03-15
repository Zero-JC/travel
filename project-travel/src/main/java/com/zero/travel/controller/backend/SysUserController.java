package com.zero.travel.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zero.travel.common.enums.StatusCode;
import com.zero.travel.common.enums.SystemConstant;
import com.zero.travel.common.response.BaseResponse;
import com.zero.travel.common.util.ValidateUtils;
import com.zero.travel.controller.CommonController;
import com.zero.travel.mapper.SysUserMapper;
import com.zero.travel.pojo.dto.LoginDTO;
import com.zero.travel.pojo.dto.SysUserDTO;
import com.zero.travel.pojo.entity.SysUser;
import com.zero.travel.service.SysUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/2/28 19:00
 */
@Controller
@RequestMapping("/backend/sysUser")
public class SysUserController extends CommonController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 登录
     * @param loginDTO
     * @param bindingResult
     * @param modelMap
     * @return
     */
    @PostMapping ("login")
    public String login(@Valid LoginDTO loginDTO, BindingResult bindingResult, Model modelMap){
        //TODO:服务端表单校验
        if (bindingResult.hasErrors()){
            String checkResult = ValidateUtils.checkResult(bindingResult);
            modelMap.addAttribute("errorMsg",checkResult);
            return "backend/login";
        }
        try {
            //TODO:登录验证
            SysUser sysUser = sysUserService.loginValidate(loginDTO);
            modelMap.addAttribute("currentUser",sysUser);
            return "backend/main";
        } catch (Exception e) {
            log.error("登录验证异常:{}",e.getMessage());
            modelMap.addAttribute("errorMsg",e.getMessage());
            return "backend/login";
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("logout")
    public String logout(){

        return "backend/login";
    }


    /**
     * 系统用户信息列表展示
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Integer pageNum,Model model){
        if (ObjectUtils.isEmpty(pageNum)){
            pageNum = SystemConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum,SystemConstant.PAGE_SIZE);
        List<SysUser> sysUserList = sysUserService.findAll();

        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUserList);
        model.addAttribute("pageInfo",pageInfo);

        return "backend/sysUserManager";
    }

    /**
     * 单查询
     * @param sysId
     * @return
     */
    @RequestMapping("/findById")
    @ResponseBody
    public BaseResponse findById(Integer sysId){
        BaseResponse response = new BaseResponse(StatusCode.Success);

        SysUser sysUser = sysUserMapper.selectByPrimaryKey(sysId);
        response.setData(sysUser);

        return response;
    }

    @RequestMapping("/search")
    public String search(Integer pageNum, SysUserDTO sysUserDTO, ModelMap modelMap){
        if (ObjectUtils.isEmpty(pageNum)){
            pageNum = SystemConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum,SystemConstant.PAGE_SIZE);
        List<SysUser> sysUserList = sysUserService.findByParam(sysUserDTO);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUserList);

        modelMap.addAttribute("pageInfo",pageInfo);
        //modelMap.addAttribute("sysUserDTO",sysUserDTO);

        return "backend/sysUserManager";
    }

}
