package com.dahantc.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Kevin Zhu on 2018/11/27 22:19 .
 */
@Component
@Slf4j
public class MyReceiver {

    //    @RabbitListener(queues= "myQueue")
    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void process(String message) {
        log.info("接受rabbitmq的消息为:" + message);
    }
    //    @RabbitListener(queues= "myQueue")


    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void computerProcess(String message) {
        log.info("接受computer rabbitmq的消息为:" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitOrder")
    ))
    public void fruitProcess(String message) {
        log.info("接受fruit rabbitmq的消息为:" + message);
    }
}
