package com.wuaiyy.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author 雾霭yy
 * @version 1.0.0
 * @date 2022/11/8 20:31
 */
public interface GatewayConstant {
    List<String> ALLOW_PATH = Arrays.asList("/oauth/token");
    String ACCESS_TOKEN = "access_token";
    String EXPIRES_IN = "expires_in";
    String AUTHORIZATION = "Authorization";
}
