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
 * @author xinjian.wu
 * @date 2018-01-30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class FanoutExchangeTest {

    @Autowired
    private Sender sender;

    private final String EXCHANGE_NAME = "fanout_exchange";

    @Test
    public void test1() throws Exception {
        sender.sendToExchange(EXCHANGE_NAME, "");
    }
}
