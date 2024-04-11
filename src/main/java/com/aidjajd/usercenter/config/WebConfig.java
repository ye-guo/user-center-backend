package com.aidjajd.usercenter.config;

import com.aidjajd.usercenter.interceptor.GlobalPreInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private GlobalPreInterceptor globalPreInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalPreInterceptor)
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns("/user/login","/user/register",
                        "/user/logout"); // 不拦截登录路径
    }
}
