package com.zero.travel.service;


import com.zero.travel.mapper.SysUserMapper;
import com.zero.travel.pojo.dto.SysUserDTO;
import com.zero.travel.pojo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/2/28 21:20
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser loginValidate(SysUserDTO sysUserDTO) throws Exception{
        SysUser sysUser = sysUserMapper.selectByUsername(sysUserDTO.getUsername());
        if (sysUser==null){
            throw new RuntimeException("当前用户不存在");
        }
        if (sysUser.getIsActive()!=1){
            throw new RuntimeException("当前用户未启用");
        }
        if (!sysUser.getPassword().equals(sysUserDTO.getPassword())){
            throw new RuntimeException("密码不正确");
        }
        return sysUser;
    }
}
