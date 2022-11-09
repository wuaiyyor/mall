package com.wuaiyy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author 雾霭yy
 * @version 1.0.0
 * @date 2022/11/8 20:07
 */
@SpringBootTest
class GatewayTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void connectRedisTest() {
    }
}
