package com.willkeep.mq.receiver;

import org.springframework.stereotype.Component;

/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2018-01-29
 */
@Component
public class Receiver3 {

    public void process(String hello) {
        System.out.println("Receiver3 : " + hello);
    }

}
