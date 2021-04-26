package com.als.score.utils;


import com.als.score.entity.User;
import com.als.score.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class JWTUtilsTest {

    @Autowired
    private JWTUtils jwtUtils;

    @Test
    public void generateToken() {
        User user = new User();
        user.setAccount("100001");
        user.setUserType("teacher");
        String token = jwtUtils.generateToken(user);
        log.info("token:{}", token);
    }

    @Test
    public void validate() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDAwMDEiLCJjcmVhdGVkIjoxNjE4Mjc4OTA0NzQxLCJ1c2VyVHlwZSI6InRlYWNoZXIiLCJleHAiOjE2MTg4ODM3MDR9.xGU9UIsRsf7jsADNZIFBSvPGcOdstF7rnpLX599CGE8";
        String userType = jwtUtils.getClaimString(token, "userType");
        log.info("userType:{}", userType);
    }
}
