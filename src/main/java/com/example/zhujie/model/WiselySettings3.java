package com.example.zhujie.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
//注入自定义的connection.properties
@ConfigurationProperties(prefix = "wisely2")
@PropertySource("classpath:connection.properties")
public class WiselySettings3 {
    //@Value("${wisely2.name333}")
    private String name333;
    private String name;

    //@Value("${wisely2.gender333}")
    private String gender333;
    private String gender;

    //@Value("${wisely2.age333}")
    private Integer age333;
    private Integer age;

}
