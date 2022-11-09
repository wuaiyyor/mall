package com.wuaiyy.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wuaiyy.constant.GatewayConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author 雾霭yy
 * @version 1.0.0
 * @date 2022/11/8 20:29
 * 回调验证后的结果拿到token存入redis中
 */
@Configuration
public class GatewayConfig {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("mall-auth", r -> r.path(GatewayConstant.ALLOW_PATH.get(0))
                        .filters(f -> f.modifyResponseBody(String.class, String.class, (exchange, result) -> {
                            JSONObject jsonObject = JSON.parseObject(result);
                            if (jsonObject.containsKey(GatewayConstant.ACCESS_TOKEN) && jsonObject.containsKey(GatewayConstant.EXPIRES_IN)) {
                                String accessToken = jsonObject.getString(GatewayConstant.ACCESS_TOKEN);
                                Long expiresIn = jsonObject.getLong(GatewayConstant.EXPIRES_IN);
                                redisTemplate.opsForValue().set(accessToken, "", Duration.ofSeconds(expiresIn));
                            }
                            return Mono.just(result);
                        })).uri("lb://mall-auth")).build();
    }

}
