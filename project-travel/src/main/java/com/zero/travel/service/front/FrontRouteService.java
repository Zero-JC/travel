package com.zero.travel.service.front;

import com.zero.travel.mapper.RouteMapper;
import com.zero.travel.pojo.entity.Route;
import com.zero.travel.pojo.vo.RouteSearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LJC
 * @version 1.0
 * @date 2021/3/20 22:09
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class FrontRouteService {

    @Autowired
    private RouteMapper routeMapper;

    public List<Route> search(RouteSearchQuery routeSearchQuery) {
        List<Route> routeList = null;
        if (routeSearchQuery == null){
            routeList = routeMapper.selectAll();
        }else {
            routeList = routeMapper.query(routeSearchQuery);
        }
        return routeList;
    }
}
