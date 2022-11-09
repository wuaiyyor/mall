package com.wuaiyy.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuaiyy.constant.GatewayConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 雾霭yy
 * @version 1.0.0
 * @date 2022/11/8 20:49
 * 判断用户是否登录
 */
@Component
public class TokenCheckFilter implements GlobalFilter, Ordered {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.判断访问路径是否是验证路径，是则放行
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        if (GatewayConstant.ALLOW_PATH.contains(path)) {
            return chain.filter(exchange);
        }
        // 2.不是登录路径，那么验证token，相同则放行
        List<String> authorizations = request.getHeaders().get(GatewayConstant.AUTHORIZATION);
        if (!CollectionUtils.isEmpty(authorizations)) {
            String authorization = authorizations.get(0);
            String jwtToken = authorization.replace("bearer ", "");
            if (StringUtils.hasText(jwtToken) && Boolean.TRUE.equals(redisTemplate.hasKey(jwtToken))) {
                return chain.filter(exchange);
            }
        }
        // 3.不相同那么就返回403错误码
        ServerHttpResponse response = exchange.getResponse();
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.UNAUTHORIZED);
        map.put("message","亲，请先认证哟");
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = new byte[0];
        try {
            bytes = objectMapper.writeValueAsBytes(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        DataBufferFactory bufferFactory = response.bufferFactory();
        DataBuffer buffer = bufferFactory.wrap(bytes);
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
