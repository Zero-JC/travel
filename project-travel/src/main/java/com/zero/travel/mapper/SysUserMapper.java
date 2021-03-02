package com.zero.travel.mapper;

import com.zero.travel.pojo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author LJC
 */
@Mapper
public interface SysUserMapper {


    int deleteByPrimaryKey(Integer sysId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer sysId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    SysUser selectByUsername(@Param("username") String username);
}