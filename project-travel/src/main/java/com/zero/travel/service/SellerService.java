package com.zero.travel.service;

import com.zero.travel.mapper.SellerMapper;
import com.zero.travel.pojo.dto.SellerDTO;
import com.zero.travel.pojo.entity.Seller;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商家的业务处理层
 * @author LJC
 * @version 1.0
 * @date 2021/3/6 21:05 propagation = Propagation.REQUIRED,rollbackFor = Exception.class
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    /**
     * 查询所有商家信息
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Seller> findAll() {
        List<Seller> sellerList = sellerMapper.selectAll();

        return sellerList;
    }

    /**
     * 新增
     * @param sellerDTO
     * @throws Exception
     */
    public void add(SellerDTO sellerDTO) throws Exception{
        Seller seller = new Seller();
        BeanUtils.copyProperties(sellerDTO,seller);

        int num = sellerMapper.insertSelective(seller);
        if (num!=1){
            throw new RuntimeException("添加失败");
        }
    }
}
