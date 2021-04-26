package com.als.score.service.impl;

import com.als.score.dto.UserForm;
import com.als.score.entity.User;
import com.als.score.service.LoginService;
import com.als.score.service.UserDetailsService;
import com.als.score.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 登录之后返回 token
     * @param userForm
     * @return
     */
    @Override
    public ResponseEntity<Map<String, Object>> login(UserForm userForm) {
        Map<String, Object> map = new HashMap<>();
        // 登录
        User user = userDetailsService.LoadUserByAccount(userForm.getAccount(), userForm.getUserType());
        if(user == null || passwordEncoder.matches(userForm.getPassword(), user.getPassword())) {
            map.put("message", "用户登录失败");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
        }

        // 更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        // 生成token
        map.put("token", jwtUtils.generateToken(user));
        map.put("tokenHead", tokenHead);
        map.put("user", user);
        return new ResponseEntity<>(map, HttpStatus.OK);

    }
}
