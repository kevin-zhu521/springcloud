package com.dahantc.controller;

import com.dahantc.config.GirlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/11/23.
 */
@RestController
@RequestMapping("/girl")
public class GrilController {

    @Autowired
    private GirlConfig girlConfig;

    @GetMapping("/getGirlInfo")
    public String getGirlInfo() {
        return girlConfig.getName() + "==" + girlConfig.getAge();
    }

}
