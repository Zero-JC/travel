package com.zero.travel.mapper;

import com.zero.travel.pojo.entity.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteMapper {


    int deleteByPrimaryKey(Integer routeId);

    int insert(Route record);

    int insertSelective(Route record);



    Route selectByPrimaryKey(Integer routeId);


    int updateByPrimaryKeySelective(Route record);

    int updateByPrimaryKey(Route record);
}