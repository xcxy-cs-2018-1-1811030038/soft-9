package com.als.score.filter;

import com.als.score.entity.User;
import com.als.score.service.UserDetailsService;
import com.als.score.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 使用token实现登录
 */
public class JWTAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从http中获取含有token的请求头信息
        String tokenAll = request.getHeader(tokenHeader);
        String header = request.getHeader("content-Type");

        // 判断信息是否符合要求
        if (tokenAll != null && tokenAll.startsWith(tokenHead)) {

            // 获取token
            String token = tokenAll.substring(tokenHead.length());

            // 从token中获取荷载account与userType
            String account = jwtUtils.getAccountByToken(token);
            String userType = jwtUtils.getClaimString(token, "userType");

            User user = userDetailsService.LoadUserByAccount(account, userType);

            // 添加到Security-context
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    //        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // 放行
        filterChain.doFilter(request, response);
    }
}
