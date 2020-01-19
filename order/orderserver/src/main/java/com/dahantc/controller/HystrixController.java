package com.dahantc.controller;

import com.dahantc.exception.OrderException;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by Kevin Zhu on 2019/1/30 14:40 .
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
@Slf4j
public class HystrixController {

    private String fallback() {
        return "太拥挤了，请稍后再试!";
    }

    private String defaultFallback() {
        return "默认提示：太拥挤了，请稍后再试!";
    }

    //    @HystrixCommand(fallbackMethod = "fallback")


//超时配置

    //    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),                //设置熔断
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),    //请求数达到后才计算
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),    //错误率
//    })
//    @HystrixCommand
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    @GetMapping("/getProductList")
    public String getProductList(@RequestParam Integer number) {
//        throw new OrderException(101, "");
        if (number % 2 == 0) {
            log.info("number==0: success");
            return "success";
        } else {
            RestTemplate restTemplate = new RestTemplate();
            log.info("number==1: success");
            return restTemplate.postForObject("http://127.0.0.1:8081/product/listForOrder", Arrays.asList("157875196366160022"), String.class);
        }
    }

}
