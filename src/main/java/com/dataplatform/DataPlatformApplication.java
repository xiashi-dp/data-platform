package com.dataplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.dataplatform.mapper")
@EnableCaching
public class DataPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataPlatformApplication.class, args);
    }
}