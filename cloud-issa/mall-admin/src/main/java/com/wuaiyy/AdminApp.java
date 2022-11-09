package com.wuaiyy;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 雾霭yy
 * @version 1.0.0
 * @date 2022/11/9 9:31
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class AdminApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminApp.class, args);
    }
}
