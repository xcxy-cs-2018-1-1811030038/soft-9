package com.als.score.config;

import com.als.score.filter.JWTAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 对资源进行放行
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/login/**",
                        "/utils/**",
                        "/",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/v3/**",
                        "/swagger-ui.html"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/student/**").hasAnyRole("STUDENT", "TEACHER", "MANAGER")
                .antMatchers("/teacher/**").hasAnyRole("TEACHER", "MANAGER")
                .antMatchers("/manager/**").hasRole("MANAGER")
                .anyRequest().authenticated()
                .and()
                // 关闭csrf
                .csrf().disable()
                // 在UsernamePasswordAuthenticationFilter过滤器前添加JWTAuthenticationTokenFilter过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                // 自定义未授权处理器
                .accessDeniedHandler(accessDeniedHandler)
                // 自定义未登录处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                // 不创建session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JWTAuthenticationTokenFilter();
    }
}
