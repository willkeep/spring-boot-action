package com.willkeep.mq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2018-01-29
 */
@Component
public class Sender {

    private AmqpTemplate rabbitTemplate;

    @Resource
    public void setRabbitTemplate(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String routingKey) {
        String context = "send1 " + new Date();
        System.out.println("Sender : " + context);
        rabbitTemplate.convertAndSend(routingKey, context);
    }

    public void sendToExchange(String exchange,String routingKey) {
        String context = "send1 " + new Date();
        System.out.println("Sender : " + context);
        rabbitTemplate.convertAndSend(exchange, routingKey, context);
    }
}
