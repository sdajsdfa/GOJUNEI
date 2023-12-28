package com.yhgc.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@MapperScan("com.yhgc.api.mapper")
@SpringBootApplication
public class PaymentAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentAppApplication.class, args);
    }

}
