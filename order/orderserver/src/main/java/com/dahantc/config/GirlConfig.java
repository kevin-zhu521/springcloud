package com.dahantc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/11/23.
 */
@Data
@Component
@ConfigurationProperties(prefix = "girl")
@RefreshScope
public class GirlConfig {

    private String name;

    private Integer age;
}
