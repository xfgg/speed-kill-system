package com.xf.demo.service;

import com.xf.demo.model.AyProduct;

import java.util.Collection;
import java.util.List;

/**
 * @author xfgg
 */
public interface AyProductService {
    /**
     * 查询所有商品
     * @return
     */
    List<AyProduct> findAll();

    /**
     * 秒杀商品
     * @param productId 商品id
     * @param userId 用户id
     * @return
     */
    AyProduct killProduct(Integer productId,Integer userId);
    /**
     * 查询所有商品
     * @return
     */
    Collection<AyProduct> findAllCache();
}
