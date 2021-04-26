package com.als.score.utils;

import java.util.Date;

import com.als.score.mapper.ManagerMapper;
import com.als.score.mapper.StudentsMapper;
import com.als.score.mapper.TeachersMapper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Autowired;

public class TokenGenerator {
    
    private Algorithm alg = Algorithm.HMAC256("aion");
    private String issu = "aion";

    @Autowired
    private StudentsMapper studentsMapper;
    @Autowired
    private TeachersMapper teachersmapper;
    @Autowired
    private ManagerMapper managerMapper;

    public String generate(String id) {
        // 当前时间
        Date currentTime = new Date();

        // 生成token，keyid为用户id
        String token = JWT.create()
                        .withIssuer(issu)
                        .withIssuedAt(currentTime)
                        .withExpiresAt(new Date(currentTime.getTime() + 1000*60*60*24))
                        .withKeyId(id)
                        .sign(alg);

        return token;
    }

    /**
     * 验证token,有效返回id，无效返回null
     * @param token
     * @return
     */
    public boolean validate(String token) {
        JWTVerifier verifier = JWT.require(alg)
                                    .withIssuer(issu)
                                    .build();

        try {
            verifier.verify(token);
            String id = resolveId(token);
            if(studentsMapper.selectById(id) != null || teachersmapper.selectById(id) != null || managerMapper.selectById(id) != null){
               return true; 
            }
            return false;
        } catch (JWTVerificationException e) {
            return false;
        }                                    
    }

    public String resolveId(String token) {
        DecodedJWT originalToken = JWT.decode(token);
        String id = originalToken.getKeyId();
        return id;
    }
}
