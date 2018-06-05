package com.example.demo.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.templateresolver.FileTemplateResolver;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/http/filter");
        super.addInterceptors(registry);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        String currPath=System.getProperty("user.dir");
        //引用全路径的文件需要加上file:
        registry.addResourceHandler("/js/**").addResourceLocations("file:"+currPath+"/web/resoures/js/");
        //registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static//web/resoures/js/");
    }

    @Bean
    //添加视图解析器
    FileTemplateResolver fileTemplateResolver() {
        FileTemplateResolver resolver = new FileTemplateResolver();
        //String currPath=System.getProperty("user.dir");
        resolver.setOrder(1);
        //绝对路径
        //resolver.setPrefix(currPath+"/web/resoures/html/");
        //相对路径
        resolver.setPrefix("./web/resoures/html/");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("LEGACYHTML5");
        return resolver;
    }

}
