package com.epam.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JWTUtil {

    static final private String key = "test";

    public static String createToken(Long userId, String username, int expireSeconds) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder()
                .claim("userId", userId)
                .claim("username", username)
                .claim("time", new Date().getTime() + expireSeconds)
//                .setExpiration(new Date())// 设置超时时间
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
            return null;
        }
    }

    public static void main(String[] args) {

        String token = JWTUtil.createToken(1L, "zhangsan", 30);
        System.out.println(token);
//        String token = "eyJhbGciOiJIUzI1NiJ9
//        .eyJ1c2VySWQiOjEsInVzZXJuYW1lIjoiemhhbmdzYW4iLCJhZ2UiOjIzLCJzZXgiOiLnlLciLCJleHAiOjE2MDY3MjYyOTB9
//        .3yrt1Njzy7FTq56oz6u4W40Jv9msh_77tubN10TLTYI";
        Claims claims = JWTUtil.parseToken(token);
        System.out.println(claims);

    }

}
