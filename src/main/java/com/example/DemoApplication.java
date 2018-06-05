package com.example;

import com.example.zhujie.model.WiselySettings;
import com.example.zhujie.model.WiselySettings2;
import com.example.zhujie.model.WiselySettings3;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.demo.mybatis")
@ServletComponentScan
@ComponentScan(basePackages = "com.example.demo")
@EnableConfigurationProperties({WiselySettings.class,WiselySettings2.class,WiselySettings3.class})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



}
