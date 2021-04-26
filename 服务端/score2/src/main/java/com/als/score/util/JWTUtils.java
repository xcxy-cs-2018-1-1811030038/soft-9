package com.als.score.util;

import com.als.score.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtils {
    private final String CLAIM_KEY_ACCOUNT = "sub";
    private final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expire}")
    private Long expire;

    /**
     * 根据用户信息生成token
     * @param user
     * @return
     */
    public String generateToken(User user) {
        Map<String, Object> claim = new HashMap<>();
        claim.put(CLAIM_KEY_CREATED, new Date());
        claim.put(CLAIM_KEY_ACCOUNT, user.getAccount());
        claim.put("userType", user.getUserType());
        return generateToken(claim);
    }

    /**
     * 从 token 中获取账号
     * @param token
     * @return
     */
    public String getAccountByToken(String token) {
        String account;
        try {
            Claims claims = getClaimsByToken(token);
            account = claims.getSubject();
        } catch (Exception e) {
            account = null;
        }
        return account;
    }

    /**
     * 获取字符串类的荷载
     * @param token
     * @param claimName
     * @return
     */
    public String getClaimString(String token, String claimName) {
        Claims claims = getClaimsByToken(token);
        return claims.get(claimName, String.class);
    }

    /**
     * 验证 token 是否有效
     * @param token
     * @param user
     * @return
     */
    public boolean validateToken(String token, User user) {
        return getAccountByToken(token).equals(user.getAccount()) && !isTokenExpired(token);
    }

    /**
     * 判断 token 是否可以被刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新 token
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsByToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 验证 token 是否过期
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDate(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从 token 中获取失效时间
     * @param token
     * @return
     */
    private Date getExpiredDate(String token) {
        Claims claims = getClaimsByToken(token);
        return claims.getExpiration();
    }

    /**
     * 从 token 中获取荷载
     * @param token
     * @return
     */
    private Claims getClaimsByToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 根据荷载生成JWT token
     * @param claim
     * @return
     */
    private String generateToken(Map<String, Object> claim) {
        return Jwts.builder()
                .setClaims(claim)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * 生成 token 失效时间
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expire * 1000);
    }


}
