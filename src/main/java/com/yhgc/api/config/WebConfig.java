package com.yhgc.api.config;

import com.yhgc.api.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
 
    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }


//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor())
//                .addPathPatterns("/**") // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
//                .excludePathPatterns(
//                        "/api/Ticket",
//                        "/api/abc"
//                );
//    }

}