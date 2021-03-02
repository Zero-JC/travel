package com.zero.travel.mapper;

import com.zero.travel.pojo.entity.Favorite;

import com.zero.travel.pojo.entity.FavoriteKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoriteMapper {


    int deleteByPrimaryKey(FavoriteKey key);

    int insert(Favorite record);

    int insertSelective(Favorite record);



    Favorite selectByPrimaryKey(FavoriteKey key);


    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);
}