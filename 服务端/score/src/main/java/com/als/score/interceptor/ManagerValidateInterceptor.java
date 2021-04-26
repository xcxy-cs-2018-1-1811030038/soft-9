package com.als.score.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.als.score.service.impl.ManagerServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ManagerValidateInterceptor implements HandlerInterceptor{

    @Autowired
    private ManagerServiceImpl managerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String uri = request.getRequestURI();
        log.debug(uri);
        String []strings = uri.split("/");
        log.debug("strings:{}", strings[2]);
        String managerId = strings[2];
        if(managerService.getById(managerId) == null) {
            request.getRequestDispatcher("/manager/error").forward(request, response);
            return false;
        } else {
            return true;
        }
    }
}
