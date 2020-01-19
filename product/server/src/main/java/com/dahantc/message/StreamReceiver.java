package com.dahantc.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * Created by Kevin Zhu on 2019/1/14 16:05 .
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(value = "myMessage1")
    public void process(Object message) {
        log.info("streamReceiver : {}", message);
    }
}
