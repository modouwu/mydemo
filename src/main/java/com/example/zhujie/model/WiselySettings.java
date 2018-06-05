package com.example.zhujie.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
//注入默认的app.properties
@ConfigurationProperties(prefix = "wisely")
public class WiselySettings {
    private String name;

    private String gender;

    private Integer age;

}
