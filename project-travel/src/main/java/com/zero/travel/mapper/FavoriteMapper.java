package com.zero.travel.mapper;

import com.zero.travel.pojo.entity.Favorite;

import com.zero.travel.pojo.entity.FavoriteKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LJC
 */
@Mapper
public interface FavoriteMapper {


    int deleteByPrimaryKey(FavoriteKey key);

    int insert(Favorite record);

    int insertSelective(Favorite record);


    Favorite selectByPrimaryKey(FavoriteKey key);


    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);

    /**
     * 根据用户id查询收藏记录
     * @param userId
     * @return
     */
    List<Favorite> selectByUserId(Integer userId);

    /**
     * 根据用户id删除收藏记录
     * @param userId
     * @return
     */
    int deleteByUserId(Integer userId);
}