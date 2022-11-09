package com.wuaiyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 雾霭yy
 * @version 1.0.0
 * @date 2022/11/8 21:40
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.wuaiyy.mapper")
public class AuthApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class, args);
    }
}
