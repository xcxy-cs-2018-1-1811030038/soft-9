package com.als.score.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.als.score.service.impl.TeachersServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * 将所有teacher路径的请求转发到manager路径
 */
@Slf4j
@Component
public class TeacherValidateInterceptor implements HandlerInterceptor{
    
    @Autowired
    private TeachersServiceImpl teachersService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String uri = request.getRequestURI();
        String []strings = uri.split("/");
        String teacherId = strings[2];
        if(teachersService.getById(teacherId) == null) {
            request.getRequestDispatcher("/teacher/error").forward(request, response);
            return false;
        } else {
            String string = strings[1] + "/" + strings[2];
            String []paths = uri.split(string);
            log.debug("path{}",paths[1]);
            request.getRequestDispatcher("/manager/666/" + paths[1]).forward(request, response);
            return false;
        }
    }
}
