package com.zero.travel.mapper;

import com.zero.travel.pojo.entity.Seller;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author LJC
 */
@Mapper
public interface SellerMapper {

    int deleteByPrimaryKey(Integer sellerId);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(Integer sellerId);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);
}