package com.zero.travel.mapper;

import com.zero.travel.pojo.entity.Route;
import com.zero.travel.pojo.vo.RouteSearchQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @author LJC
 */
@Mapper
public interface RouteMapper {


    int deleteByPrimaryKey(Integer routeId);

    int insert(Route record);

    int insertSelective(Route record);



    Route selectByPrimaryKey(Integer routeId);


    int updateByPrimaryKeySelective(Route record);

    int updateByPrimaryKey(Route record);

    /**
     * 查询所有
     * @return
     */
    List<Route> selectAll();

    /**
     * 条件查询
     * @param route
     * @return
     */
    List<Route> selectByParam(Route route);

    /**
     * 前台查询
     * @param routeSearchQuery
     * @return
     */
    List<Route> query(RouteSearchQuery routeSearchQuery);

    /**
     * 根据线路名称查询
     * @param routeName
     * @return
     */
    Route selectByRouteName(String routeName);
}