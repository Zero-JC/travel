package com.zero.travel.mapper;

import com.zero.travel.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author LJC
 */
@Mapper
public interface UserMapper {


    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);



    User selectByPrimaryKey(Integer userId);


    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}