package com.example.demo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebComponentConfig {
    @Bean
    public FilterRegistrationBean filterDemo3Registration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(SessionFilter());
        registration.addUrlPatterns("/http/filter");
        registration.addInitParameter("paramName", "12345678");
        registration.setName("SessionFilter");
        registration.setOrder(6);
        return registration;
    }

    @Bean
    public Filter SessionFilter(){
        return new SessionFilter();
    }
}
