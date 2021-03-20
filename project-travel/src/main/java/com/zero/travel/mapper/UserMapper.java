package com.zero.travel.mapper;

import com.zero.travel.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author LJC
 */
@Mapper
public interface UserMapper {


    /**
     * 删除
     * @param userId
     * @return
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 选择条件新增
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据id查询
     * @param userId
     * @return
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * 选择条件更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 查询所有
     * @return
     */
    List<User> selectFindAll();

    /**
     * 多条件查询
     * @param userParam
     * @return
     */
    List<User> selectByParam(User userParam);

    /**
     * 修改客户状态
     * @param status
     * @param userId
     * @return
     */
    int updateStatus(@Param("status") Integer status, @Param("userId") Integer userId);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User selectByUsername(@Param("username") String username);
}