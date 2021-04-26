package com.als.score.controller;

import java.util.HashMap;
import java.util.Map;

import com.als.score.entity.Response;
import com.als.score.service.ValidateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/logins")
public class LoginController {

    @Autowired
    private ValidateService validateService;

    @PostMapping("/student")
    public Response login(
        @RequestParam(value = "userType", required = true)String userType,
        @RequestParam(value = "account", required = true)String account,
        @RequestParam(value = "password", required = true)String password
    ) {
        log.debug("userType:{}", userType);
        log.debug("account:{}", account);
        log.debug("password:{}", password);
        
        Map<String, Object> map = new HashMap<>();
        if((map=validateService.validate(userType, account, password)) != null){
            return Response.success("登录成功", map);
        }
        return Response.error("登录失败");
    }

    @RequestMapping("/error")
    public Response returnError() {
        return Response.error("未登录");
    }
}
