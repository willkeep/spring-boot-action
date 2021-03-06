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
public class DirectExchangeTest {

    @Autowired
    private Sender sender;

    @Test
    public void test1() throws Exception {
        sender.send("key1");
    }

    @Test
    public void test11() throws Exception {
        sender.send("key2");
    }

    @Test
    public void test2() throws Exception {
        sender.send("queue_name_1");
    }

    @Test
    public void test21() throws Exception {
        sender.send("queue_name_2");
    }

    @Test
    public void test3() throws Exception {
        sender.sendToExchange("exchange_name_3","queue_name_1");
    }

    @Test
    public void test4() throws Exception {
        sender.sendToExchange("","key2");
    }

    @Test
    public void test5() throws Exception {
        sender.sendToExchange("exchange_name_1","key2");
    }

    @Test
    public void test6() throws Exception {
        sender.sendToExchange("exchange_name_2","key2");
    }
}