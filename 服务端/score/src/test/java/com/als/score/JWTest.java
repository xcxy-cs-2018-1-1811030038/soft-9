package com.als.score;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTest {

    @Test
    public void test() {
        String key = "key";
        Algorithm algorithm = Algorithm.HMAC256(key);

        Date current = new Date();
        String token = JWT.create()
                            .withIssuer("aion")
                            .withSubject("subject")
                            .withIssuedAt(current)
                            .withKeyId(key)
                            .withExpiresAt(new Date(current.getTime() + 60*1000))
                            .sign(algorithm);
        log.debug(token.toString());

        JWTVerifier verifier = JWT.require(algorithm)
                                    // .withIssuer("aion")
                                    .withSubject("subject")
                                    .build();
        
        try {
            verifier.verify(token);
            log.debug("验证通过");
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            log.debug("验证失败");
        }

        try {
            DecodedJWT originToken = JWT.decode(token);
            log.debug("issuer:{}", originToken.getIssuer());
            log.debug("key:{}", originToken.getKeyId());
        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
    }
}
