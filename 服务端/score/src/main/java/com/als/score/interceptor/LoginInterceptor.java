package com.als.score.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.als.score.utils.TokenGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor{
    
    @Autowired
    private TokenGenerator tokenGenerator;

    /**
     * 验证token是否有效
     * 如果无效转发到/login/error
     * 如果有效解析token的id，并将id作为添加为请求参数
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String token = request.getHeader("token");
        if(token == null) {
            request.getRequestDispatcher("/logins/error").forward(request, response);
            return false;
        }
        if(!tokenGenerator.validate(token)) {
            request.getRequestDispatcher("/logins/error").forward(request, response);
            return false;
        }
        // 放行
        return true;
    }    

}
