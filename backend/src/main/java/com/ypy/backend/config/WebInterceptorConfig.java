package com.ypy.backend.config;

import com.ypy.backend.utils.interceptor.AdminInterceptor;
import com.ypy.backend.utils.interceptor.AuthInterceptor;
import com.ypy.backend.utils.interceptor.UserStatusInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Swagger 依赖的 api 如下, 添加 @Profile("prod"), 仅在生成模式下开启拦截器
 * 不然, 如要使用swagger, 必须在每个拦截器中开放以下端口
 *
 * "/swagger-ui/**",
 * "/v3/api-docs/**",
 * "/swagger-resources/**",
 * "/webjars/**",
 * "/swagger-ui.html",
 * "/favicon.ico"
 */
@Profile("prod")
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private AdminInterceptor adminInterceptor;

    @Autowired
    private UserStatusInterceptor userStatusInterceptor;

    // 各种拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        // user
                        "/api/user/login",
                        "/api/user/register",
                        // admin
                        "/api/admin/login"
                );

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/api/admin/**")
                .excludePathPatterns(
                        // admin
                        "/api/admin/login"
                );

        registry.addInterceptor(userStatusInterceptor)
                .addPathPatterns("/api/user/**")
                .excludePathPatterns(
                        "/api/user/login",
                        "/api/user/register"
                );
    }
}
