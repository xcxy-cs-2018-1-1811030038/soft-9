package com.als.score.config;

import com.als.score.interceptor.LoginInterceptor;
import com.als.score.utils.TokenGenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Myconfig {
    @Bean
    public TokenGenerator getTokenGenerator() {
        return new TokenGenerator();
    }

    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }
}
