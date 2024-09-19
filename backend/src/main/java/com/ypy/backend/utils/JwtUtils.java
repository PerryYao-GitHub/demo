package com.ypy.backend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    // 7天过期
    private static final long expire = 60 * 60 * 24 * 7;  // s 为单位
    // 32位密钥
    private static final String secret = "jfrdscvgylmqwacxzygreslkopugbsrd";

    // 生成 admin token
    public static String genToken(Integer id, Byte role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("role", role);

        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }
/**
    setClaims：
    setClaims 方法用于设置 JWT 的声明部分（claims）。
    JWT 的声明部分可以包含多个键值对，用于存储多个属性（
    例如用户的 ID、用户名、角色等）。
    这是一个 Map<String, Object>类型，你可以放置任意多的键值对，
    以描述该 token
    的所有信息。

    setSubject：
    setSubject 方法用于设置
    JWT 的主题部分（subject）。这个字段通常用于存储一个标识用户的信息，
    例如用户名或用户 ID。
    这是一个单一的字符串值，适合用来存储简单的标识符。
*/
    // 解析token
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
