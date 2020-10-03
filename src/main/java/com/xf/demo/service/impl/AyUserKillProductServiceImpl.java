package com.xf.demo.service.impl;

import com.xf.demo.model.AyUserKillProduct;
import com.xf.demo.repository.AyUserKillProductRepository;
import com.xf.demo.service.AyUserKillProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xfgg
 */
@Service
public class AyUserKillProductServiceImpl implements AyUserKillProductService {
    @Resource
    private AyUserKillProductRepository ayUserKillProductRepository;
    @Override
    public AyUserKillProduct save(AyUserKillProduct ayUserKillProduct) {
        return ayUserKillProductRepository.save(ayUserKillProduct);
    }
}
