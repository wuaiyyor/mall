package com.wuaiyy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 雾霭yy
 * @version 1.0.0
 * @date 2022/11/9 11:45
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ManagerApp {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApp.class, args);
    }
}
