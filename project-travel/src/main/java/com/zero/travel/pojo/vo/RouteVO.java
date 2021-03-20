package com.zero.travel.pojo.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;

/**
 * 旅游线路视图对象
 * @author LJC
 * @version 1.0
 * @date 2021/3/17 17:45
 */
@Data
public class RouteVO implements Serializable {


    private Integer routeId;

    /**
     * 线路名称
     */
    private String routeName;

    /**
     * 参考价格
     */
    private Double price;

    /**
     * 线路简介
     */
    private String routeIntroduce;

    /**
     * 景点图片
     */
    ///private CommonsMultipartFile file;

    /**
     * 攻略
     */
    private String strategy;

    /**
     * 所属商家
     */
    private Integer sellerId;



}
