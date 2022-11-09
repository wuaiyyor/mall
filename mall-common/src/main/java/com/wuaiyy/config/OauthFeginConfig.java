package com.wuaiyy.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 雾霭yy
 * @version 1.0.0
 * @date 2022/11/9 10:04
 */
@Component
public class OauthFeginConfig implements RequestInterceptor {

    public static final String AUTHORIZATION = "Authorization";
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        String authorization = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(authorization)) {
            requestTemplate.header(AUTHORIZATION,authorization);
        }
        requestTemplate.header(AUTHORIZATION,"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJjbGllbnQtc2NvcGVzIl0sImV4cCI6MTY2ODAwNDE5MCwianRpIjoiNWExMDFmM2UtODFmZS00ZmMwLWI1NjItY2YzY2ZiZGQ3MGMyIiwiY2xpZW50X2lkIjoiY2xpZW50In0.iCsf7m95ZyOTxxM3xSvSA27YCV5rOx8PPTFyQLQvNyOyNZfq4cHQbc4DgwawlzpYIVjgwnpL_IE_1SOfEbJbDBVVYae8-eUagndxf8S6aN9YoupnQ3SLdUs33w7e4ErYLS30iScYW300dZTVqM_QRg3KYIPC35ap-YiVdgZK0WCXw3f4qbV08_m3A6PsR1ASwTnVpYfcVJ0x6TBFc7ysWGlXyqHVqGDQltGb7B_4lkTlLmyr9XesJtBOzGfmGfZFsLZoPDqWFx76IiY5P4jbzI0HX6joSHjCnYD5EfqTsMnX7Ivn-xhEM8Caubo38xX9kY5CBsf9jAmnqmtawQ9EUw");
    }
}
