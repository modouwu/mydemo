package com.example.zhujie.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
//注入自定义的connection.properties
@ConfigurationProperties(prefix = "wisely2")
@PropertySource("classpath:connection.properties")
public class WiselySettings2 {
    private String name;

    private String gender;

    private Integer age;
}
