package com.zero.travel.mapper;

import com.zero.travel.pojo.entity.RouteFavorite;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RouteFavoriteMapper {


    int deleteByPrimaryKey(Integer routeId);

    int insert(RouteFavorite record);

    int insertSelective(RouteFavorite record);

    RouteFavorite selectByPrimaryKey(Integer routeId);

    int updateByPrimaryKeySelective(RouteFavorite record);

    int updateByPrimaryKey(RouteFavorite record);

    /**
     * 根据线路id获取收藏次数
     * @param routeId
     * @return
     */
    int selectCount(Integer routeId);
}