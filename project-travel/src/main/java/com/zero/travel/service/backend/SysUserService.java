package com.zero.travel.service.backend;


import com.zero.travel.mapper.SysUserMapper;
import com.zero.travel.pojo.dto.LoginDTO;
import com.zero.travel.pojo.dto.SysUserDTO;
import com.zero.travel.pojo.entity.SysUser;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/2/28 21:20
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 登录验证
     * @param loginDTO
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public SysUser loginValidate(LoginDTO loginDTO) throws Exception{
        SysUser sysUser = sysUserMapper.selectByUsername(loginDTO.getUsername());
        if (sysUser==null){
            throw new RuntimeException("当前用户不存在");
        }
        if (sysUser.getIsActive()!=1){
            throw new RuntimeException("当前用户未启用");
        }
        if (!sysUser.getPassword().equals(loginDTO.getPassword())){
            throw new RuntimeException("密码不正确");
        }
        return sysUser;
    }

    /**
     * 查询所有
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<SysUser> findAll() {
        List<SysUser> list = sysUserMapper.selectAll();

        return list;
    }

    /**
     * 条件查询
     * @param sysUserDTO
     * @return
     */
    public List<SysUser> findByParam(SysUserDTO sysUserDTO) {
        if (ObjectUtils.isNotEmpty(sysUserDTO)){
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(sysUserDTO,sysUser);

            List<SysUser> list = sysUserMapper.selectByParam(sysUser);
            return list;
        }
        return null;
    }

    /**
     * 新增用户
     * @param sysUserDTO
     */
    public void add(SysUserDTO sysUserDTO) throws Exception{
        SysUser regSysUser = sysUserMapper.selectByUsername(sysUserDTO.getUsername());
        if (regSysUser!=null){
            throw new RuntimeException("账号已存在");
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDTO,sysUser);
        //默认有效状态
        sysUser.setIsActive(1);

        sysUserMapper.insertSelective(sysUser);
    }

    /**
     * 修改用户信息
     * @param sysUserDTO
     */
    public void modify(SysUserDTO sysUserDTO) throws Exception{
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDTO,sysUser);
        //补充id
        if (sysUser.getSysId() == null){
            Integer sysId = sysUserMapper.selectByUsername(sysUserDTO.getUsername()).getSysId();
            sysUser.setSysId(sysId);
        }

        int row = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (row != 1){
            throw new RuntimeException("修改失败");
        }
    }

    /**
     * 修改状态
     * @param sysId
     */
    public void modifyStatus(Integer sysId) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(sysId);
        Integer isActive = null;
        if (sysUser.getIsActive() == 1){
            isActive = 0;
        }

        if (sysUser.getIsActive() == 0){
            isActive = 1;
        }
        int row = sysUserMapper.updateByIsActive(isActive,sysId);
        if (row != 1){
            throw new RuntimeException("修改失败");
        }

    }
}
