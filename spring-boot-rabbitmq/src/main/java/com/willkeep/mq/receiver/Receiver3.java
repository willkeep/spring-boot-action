package com.willkeep.mq.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2018-01-29
 */
@Component
public class Receiver3 implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String body = new String(message.getBody(), "utf-8");
        System.out.println("Receiver3 : " + body);
    }
}
