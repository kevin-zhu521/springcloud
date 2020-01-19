package com.dahantc.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Kevin Zhu on 2019/1/14 16:02 .
 */
public interface StreamClient {

//    @Input("myMessage1")
//    SubscribableChannel input();

    @Output("myMessage1")
    MessageChannel output();
}
