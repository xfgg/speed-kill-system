package com.xf.demo.producer;

import com.xf.demo.model.AyUserKillProduct;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author xfgg
 */
@Service
public class AyProductKillProducer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 发送信息
     * @param destination 目的地址
     * @param killProduct 描述商品
     */
    public void sendMessage(Destination destination, final AyUserKillProduct killProduct){
        jmsMessagingTemplate.convertAndSend(destination,killProduct);
    }
}
