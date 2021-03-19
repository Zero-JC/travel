package com.zero.travel;

import com.zero.travel.pojo.dto.RouteDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectTravelApplicationTests {

    @Test
    void contextLoads() {
        RouteDTO routeDTO = new RouteDTO();
        RouteDTO route = new RouteDTO();
        route.setRouteId(101);
        route.setRouteName("测试");

        if (ObjectUtils.isEmpty(routeDTO)){
            System.out.println("----对象为空");
        }

        if (ObjectUtils.isEmpty(route)){
            System.out.println("----对象为空");
        }
    }

}
