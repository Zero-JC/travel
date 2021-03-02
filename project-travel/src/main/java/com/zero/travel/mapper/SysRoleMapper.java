package com.zero.travel.mapper;

import com.zero.travel.pojo.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {


    int deleteByPrimaryKey(Integer roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);



    SysRole selectByPrimaryKey(Integer roleId);



    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}