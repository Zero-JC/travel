package com.zero.travel.pojo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.io.Serializable;

/**
 * 旅游线路视图对象
 * @author LJC
 * @version 1.0
 * @date 2021/3/17 17:45
 */
@Data
public class RouteDTO implements Serializable {


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
    private MultipartFile imageFile;

    /**
     * 图片url
     */
    private String imageUrl;

    /**
     * 攻略
     */
    private String strategy;

    /**
     * 所属商家
     */
    private Integer sellerId;


}
