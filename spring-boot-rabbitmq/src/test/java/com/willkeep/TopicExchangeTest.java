package com.willkeep;

import com.willkeep.mq.sender.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>Description:</p>
 *
 * @author heng
 * @date 2018-01-29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TopicExchangeTest {

    @Autowired
    private Sender sender;

    private final String EXCHANGE_NAME = "topic_exchange";

    @Test
    public void test1() throws Exception {
        sender.sendToExchange(EXCHANGE_NAME, "xxx.a.log.xxx");
    }

    @Test
    public void test2() throws Exception {
        sender.sendToExchange(EXCHANGE_NAME, "xxx.b.log.xxx");
    }

    @Test
    public void test3() throws Exception {
        sender.sendToExchange(EXCHANGE_NAME, "xxx.c.log.xxx");
    }

}