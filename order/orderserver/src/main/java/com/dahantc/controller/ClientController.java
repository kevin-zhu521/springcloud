package com.dahantc.controller;

import com.dahantc.client.ProductClient;
import com.dahantc.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kevin Zhu on 2018/11/5 22:03 .
 * 测试restTemplate和feign时使用
 */
@RestController
@Slf4j
public class ClientController {

    //    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private ProductClient productClient;

    @GetMapping("getProductMsg")
    public String getProductMsg() {
        /**
         * 使用restTemplate请求(demo)
         */
//        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//        log.info("response={}", response);
//        return response;
        /**
         * 使用feign请求(demo)
         */
        String response = productClient.productMsg();
        log.info("getMsg的response={}", response);
        return response;
    }

    @GetMapping("getProductInfo")
    public List<ProductInfoOutput> getProductInfo() {
        List<ProductInfoOutput> response = productClient.listForOrder(Arrays.asList("157875196366160022"));
        log.info("getProductInfo的response={}", response);
        return response;
    }

    @GetMapping("productDecreaseStock")
    public String productDecreaseStock() {
//        productClient.decreaseStock(Arrays.asList(new CartDTO("157875196366160022", 3)));
        return "ok";
    }
}
