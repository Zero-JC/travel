package com.zero.travel.mapper;

import com.zero.travel.pojo.entity.Seller;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


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

    /**
     * 查询所有
     * @return
     */
    List<Seller> selectAll();
}