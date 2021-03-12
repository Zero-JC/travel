package com.zero.travel.service;

import com.zero.travel.mapper.SellerMapper;
import com.zero.travel.pojo.entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商家的业务处理层
 * @author LJC
 * @version 1.0
 * @date 2021/3/6 21:05
 */
@Service
public class SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    /**
     * 查询所有商家信息
     * @return
     */
    public List<Seller> findAll() {
        List<Seller> sellerList = sellerMapper.selectAll();

        int i = 10/0;

        return sellerList;
    }
}
