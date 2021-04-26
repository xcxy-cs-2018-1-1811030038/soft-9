package com.als.score.config;

import com.als.score.interceptor.LoginInterceptor;
import com.als.score.interceptor.ManagerValidateInterceptor;
import com.als.score.interceptor.TeacherValidateInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyWebConfig implements WebMvcConfigurer{
    
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private ManagerValidateInterceptor mvIntercepter;

    @Autowired
    private TeacherValidateInterceptor tvInterceptor;

    /**
     * 注册拦截器
     * 越先注册的拦截器优先级越高
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                // 添加将要拦截的路径
                .addPathPatterns("/**")
                // 放行不需要的拦截的路径
                .excludePathPatterns("/", "/logins/**");

        registry.addInterceptor(mvIntercepter)
                .addPathPatterns("/manager/**")
                .excludePathPatterns("/manager/error");
        
        registry.addInterceptor(tvInterceptor)
                .addPathPatterns("/teacher/**")
                .excludePathPatterns("/*", "/teacher/error");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
