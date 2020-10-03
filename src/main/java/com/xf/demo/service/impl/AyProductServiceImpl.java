package com.xf.demo.service.impl;

import com.xf.demo.model.AyProduct;
import com.xf.demo.model.AyUserKillProduct;
import com.xf.demo.model.KillStatus;
import com.xf.demo.producer.AyProductKillProducer;
import com.xf.demo.repository.AyProductRepository;
import com.xf.demo.service.AyProductService;
import com.xf.demo.service.AyUserKillProductService;
import lombok.val;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author xfgg
 */
@Service
public class AyProductServiceImpl implements AyProductService {
    @Resource
    private AyProductRepository ayProductRepository;
    @Resource
    private AyUserKillProductService ayUserKillProductService;
    @Override
    public List<AyProduct> findAll() {
        return ayProductRepository.findAll();
    }

    /**
     * 秒杀商品
     * @param productId 商品id
     * @param userId 用户id
     * @return
     */
    @Resource
    private AyProductKillProducer ayProductKillProducer;
    private static Destination destination=new ActiveMQQueue("ay.queue.asyn.sava");
    @Override
    public AyProduct killProduct(Integer productId, Integer userId) {
        //查询商品
        AyProduct ayProduct=ayProductRepository.findById(productId).get();
        //判断商品是否还有库存
        if(ayProduct.getNumber()<0) {
            return null;
        }
        //设置商品的库存：原库存数量-1
        ayProduct.setNumber(ayProduct.getNumber()-1);
        //更新商品库存
        ayProduct = ayProductRepository.save(ayProduct);
        //保存商品秒杀记录
        AyUserKillProduct ayUserKillProduct=new AyUserKillProduct();
        ayUserKillProduct.setCreateTime(new Date());
        ayUserKillProduct.setProductId(productId);
        ayUserKillProduct.setUserId(userId);
        //设置秒杀状态
        ayUserKillProduct.setState(KillStatus.SUCCESS.getCode());
        //保存秒杀记录详细信息
        //ayUserKillProductService.save(ayUserKillProduct);
        //异步保存商品的秒杀记录
        ayProductKillProducer.sendMessage(destination,ayUserKillProduct);
        redisTemplate.opsForHash().put(KILL_PRODUCT_LIST, ayUserKillProduct.getProductId(),ayProduct);
        return ayProduct;
    }
    @Resource
    private RedisTemplate redisTemplate;
    /**
     * 定义缓存key
     */
    private static final String KILL_PRODUCT_LIST = "kill_product_list";
    @Override
    public Collection<AyProduct> findAllCache() {
        try {
            Map<Integer, AyProduct> productMap = redisTemplate.opsForHash().entries(KILL_PRODUCT_LIST);
            Collection<AyProduct> ayProducts = null;
            if (CollectionUtils.isEmpty(productMap)) {
                ayProducts = ayProductRepository.findAll();
                productMap = converToMap(ayProducts);
                redisTemplate.opsForHash().putAll(KILL_PRODUCT_LIST, productMap);
                //设置缓存过期时间
                redisTemplate.expire(KILL_PRODUCT_LIST, 10000, TimeUnit.MILLISECONDS);
                return ayProducts;
            }
            ayProducts = productMap.values();
            return ayProducts;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * List转换为Map
     * @param ayProducts
     * @return
     */
    private Map<Integer,AyProduct> converToMap(Collection<AyProduct> ayProducts){
        if(CollectionUtils.isEmpty(ayProducts)){
            return (Map<Integer, AyProduct>) Collections.EMPTY_LIST;
        }
        Map<Integer,AyProduct> productMap = new HashMap<>(ayProducts.size());
        for (AyProduct product: ayProducts
             ) {
            productMap.put(product.getId(),product);
        }
        return productMap;
    }
}
