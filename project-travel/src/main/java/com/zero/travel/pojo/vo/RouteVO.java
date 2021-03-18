package com.zero.travel.pojo.vo;

import lombok.Data;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "线路名称不能为空")
    private String routeName;

    /**
     * 参考价格
     */
    @NotNull(message = "参考价格不能为空")
    private Integer price;

    /**
     * 线路简介
     */
    @NotBlank(message = "线路简介不能为空")
    private String routeIntroduce;

    /**
     * 景点图片
     */
    private CommonsMultipartFile file;

    /**
     * 攻略
     */
    @NotBlank(message = "攻略不能为空")
    private String strategy;

    /**
     * 所属商家
     */
    private Integer sellerId;



}
