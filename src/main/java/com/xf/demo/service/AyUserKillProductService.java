package com.xf.demo.service;

import com.xf.demo.model.AyUserKillProduct;

/**
 * @author xfgg
 */
public interface AyUserKillProductService {
    /**
     * 保存用户秒杀商品记录
     * @param ayUserKillProduct
     * @return
     */
    AyUserKillProduct save(AyUserKillProduct ayUserKillProduct);
}
