package com.zero.travel.mapper;

import com.zero.travel.pojo.entity.SysUser;


import java.util.List;

public interface SysUserMapper {


    int deleteByPrimaryKey(Integer sysId);

    int insert(SysUser record);

    int insertSelective(SysUser record);



    SysUser selectByPrimaryKey(Integer sysId);


    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}