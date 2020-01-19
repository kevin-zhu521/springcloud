package com.dahantc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kevin Zhu on 2018/11/5 21:58 .
 * 测试restTemplate时使用
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        return "this is PRODUCT` msg";
    }

}
