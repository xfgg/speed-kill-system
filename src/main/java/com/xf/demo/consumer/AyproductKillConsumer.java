package com.xf.demo.consumer;

import com.xf.demo.model.AyUserKillProduct;
import com.xf.demo.service.AyUserKillProductService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xfgg
 */
@Component
public class AyproductKillConsumer {
    @Resource
    private AyUserKillProductService ayUserKillProductService;
    @JmsListener(destination = "ay.queue.asyn.save")
    public void receiveQueue(AyUserKillProduct killProduct){
        //保存商品秒杀信息
        ayUserKillProductService.save(killProduct);

    }
}
