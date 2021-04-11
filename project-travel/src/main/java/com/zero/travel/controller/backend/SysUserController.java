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
import com.zero.travel.service.backend.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/2/28 19:00
 */
@Controller
@RequestMapping("/backend/sysUser")
@Api(tags = "系统用户管理接口")
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
    @ApiOperation("后台管理系统登录")
    public String login(@Valid LoginDTO loginDTO, BindingResult bindingResult, Model modelMap, HttpSession session){
        //TODO:服务端表单校验
        if (bindingResult.hasErrors()){
            String checkResult = ValidateUtils.checkResult(bindingResult);
            modelMap.addAttribute("errorMsg",checkResult);
            return "backend/login";
        }
        try {
            //TODO:登录验证
            SysUser sysUser = sysUserService.loginValidate(loginDTO);
            session.setAttribute("currentSysUser",sysUser);
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
    @ApiOperation("logout")
    @GetMapping(value = "logout")
    public String logout(HttpSession session){
        session.removeAttribute("currentSysUser");

        return "backend/login";
    }


    /**
     * 系统用户信息列表展示
     * @param pageNum
     * @param model
     * @return
     */
    @ApiOperation("系统用户信息列表展示")
    @GetMapping("/findAll")
    public String findAll(Integer pageNum,Model model){
        if (ObjectUtils.isEmpty(pageNum)){
            pageNum = SystemConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum,SystemConstant.PAGE_SIZE);
        List<SysUser> sysUserList = sysUserService.findAll();

        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUserList);
        model.addAttribute("pageInfo",pageInfo);

        SysUserDTO sysUserDTO = new SysUserDTO();
        model.addAttribute("sysUserDTO",sysUserDTO);

        return "backend/sysUserManager";
    }

    /**
     * 单查询
     * @param sysId
     * @return
     */
    @PostMapping("/findById")
    @ResponseBody
    @ApiOperation("根据ID查询")
    public BaseResponse findById( Integer sysId){
        BaseResponse response = new BaseResponse(StatusCode.Success);

        SysUser sysUser = sysUserMapper.selectByPrimaryKey(sysId);
        response.setData(sysUser);

        return response;
    }

    /**
     * 多条件搜索
     * @param pageNum
     * @param sysUserDTO
     * @param modelMap
     * @return
     */
    @PostMapping("/search")
    @ApiOperation("条件搜索")
    public String search(Integer pageNum, SysUserDTO sysUserDTO, ModelMap modelMap){
        if (ObjectUtils.isEmpty(pageNum)){
            pageNum = SystemConstant.PAGE_NUM;
        }

        PageHelper.startPage(pageNum,SystemConstant.PAGE_SIZE);
        List<SysUser> sysUserList = sysUserService.findByParam(sysUserDTO);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUserList);

        modelMap.addAttribute("pageInfo",pageInfo);
        modelMap.addAttribute("sysUserDTO",sysUserDTO);

        return "backend/sysUserManager";
    }

    /**
     * 新增
     * @param sysUserDTO
     * @param result
     * @return
     */
    @ApiOperation("新增系统用户信息")
    @PostMapping("/add")
    @ResponseBody
    public BaseResponse add(@Valid SysUserDTO sysUserDTO,BindingResult result){
        try {
            if (result.hasErrors()){
                String checkResult = ValidateUtils.checkResult(result);
                return new BaseResponse(StatusCode.InvalidParams.getCode(),checkResult);
            }
            sysUserService.add(sysUserDTO);
            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            log.error(e.toString());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    /**
     * 修改
     * @param sysUserDTO
     * @param result
     * @return
     */
    @ApiOperation("修改系统用户信息")
    @PostMapping(value = "/modify")
    @ResponseBody
    public BaseResponse modify(@Valid SysUserDTO sysUserDTO,BindingResult result) {
        try {
            if (result.hasErrors()){
                String checkResult = ValidateUtils.checkResult(result);
                log.warn("参数校验失败: {}",checkResult);
                return new BaseResponse(StatusCode.InvalidParams.getCode(),checkResult);
            }
            sysUserService.modify(sysUserDTO);

            return new BaseResponse(StatusCode.Success);
        } catch (Exception e) {
            log.error("修改系统用户信息异常:{}",e.toString());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

    /**
     * 账户的启用禁用
     * @param sysId
     * @return
     */
    @ApiOperation("账户的启用禁用")
    @PostMapping("/modifyStatus")
    @ResponseBody
    public BaseResponse modifyStatus(Integer sysId){
        try {
            if (ObjectUtils.isEmpty(sysId)){
                return new BaseResponse(StatusCode.InvalidParams);
            }
            sysUserService.modifyStatus(sysId);
            return new BaseResponse(StatusCode.Success);
        }catch (Exception e){
            log.error("账户的启用禁用:{}",e.getMessage());
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
    }

}
