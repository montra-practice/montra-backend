package com.epam.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Slf4j
public class JWTUtil {

    // TOKEN的有效期一小时（S）
    private static final int TOKEN_TIME_OUT = 360000;

    static final private String key = "test";

    public static String createToken(Long userId, String username) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        long currentTime = System.currentTimeMillis();

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder()
                .claim("userId", userId)
                .claim("username", username)
                .setIssuedAt(new Date(currentTime))  //签发时间
                .setExpiration(new Date(currentTime + TOKEN_TIME_OUT * 1000))  //过期时间戳
                .signWith(signatureAlgorithm, signingKey);

        //生成JWT
        return builder.compact();
    }


    public static Claims parseToken(String token) {
        if ("".equals(token)) {
            return null;
        }

        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                    .parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    public static void main(String[] args) {
        String bb = createToken(1L, "bb");
        Claims claims = parseToken(bb);
        System.out.println(bb);
    }

}
