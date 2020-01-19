package com.dahantc;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


/**
 * Created by Kevin Zhu on 2018/11/27 22:23 .
 */
public class RabbitMQTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendMessage() {
        amqpTemplate.convertAndSend("myQueue","现在的时间:" + new Date());
    }
    @Test
    public void sendComputerMessage() {
        amqpTemplate.convertAndSend("myOrder","computer","现在的时间:" + new Date());
    }
}
