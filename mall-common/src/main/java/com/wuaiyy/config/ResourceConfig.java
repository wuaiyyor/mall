package com.wuaiyy.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.nio.charset.Charset;

/**
 * @author 雾霭yy
 * @version 1.0.0
 * @date 2022/11/9 9:40
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenstore());
    }

    @Bean
    public TokenStore tokenstore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        ClassPathResource classPathResource = new ClassPathResource("publicKey.txt");
        String publicKey = FileUtil.readString(classPathResource.getFile(), Charset.defaultCharset());
        jwtAccessTokenConverter.setVerifierKey(publicKey);
        return jwtAccessTokenConverter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http.sessionManagement().disable();
        http.authorizeRequests()
                .antMatchers("/v2/api-docs",
                "/v3/api-docs",
                "/swagger-resources/configuration/ui",
                 //用来获取api-docs的URI
                "/swagger-resources",
                  //安全选项
                "/swagger-resources/configuration/security",
                "/webjars/**",
                "/swagger-ui/**",
                "/druid/**",
                "/actuator/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
