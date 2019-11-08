//package com.liaoin.muti.test.helper.commone;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.val;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.util.Base64Utils;
//
//import javax.crypto.SecretKey;
//import java.io.IOException;
//import java.util.Date;
//
///**
// * JWT 工具
// */
//@Setter
//public class JwtHelper {
//
//    // 私钥
//    @Value("${jwt.key}")
//    private String key;
//
//    // 客户端ID
//    @Value("${jwt.clientId}")
//    private String clientId;
//
//    // 超时时间，单位秒
//    @Value("${jwt.timeout}")
//    private Long timeout;
//
//    private ObjectMapper objectMapper;
//
//    public JwtHelper(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    /**
//     * 生成 JWT Token
//     *
//     * @param subject 数据
//     * @return token
//     */
//    public String general(Subject subject) {
//        val signatureAlgorithm = SignatureAlgorithm.HS256;
//        val nowMillis = System.currentTimeMillis();
//        val now = new Date(nowMillis);
//        val key = key();
//        String message = "";
//        try {
//            message = objectMapper.writeValueAsString(subject);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        JwtBuilder builder = Jwts.builder()
//                .setId(clientId)
//                .setIssuedAt(now)
//                .setSubject(message)
//                .signWith(key, signatureAlgorithm);
//        val exp = new Date(nowMillis + timeout * 1000);
//        builder.setExpiration(exp);
//        return builder.compact();
//    }
//
//    /**
//     * 解析Token
//     *
//     * @param jwt token
//     * @return 解析结果
//     */
//    public Claims parse(String jwt) {
////        try {
//        // ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException
//        return Jwts.parser().setSigningKey(key()).parseClaimsJws(jwt).getBody();
////        } catch (SecurityException ice) {
////            throw ice;
////        } catch (IllegalArgumentException ice) {
////            com.liaoin.service.core.com.liaoin.service.controller.log.error("IllegalArgumentException");
////        }
////        return null;
//    }
//
//    /**
//     * 解析Token为subject
//     *
//     * @param jwt token
//     * @return 解析结果
//     */
//    public Subject subject(String jwt) {
//        val subject = Jwts.parser().setSigningKey(key()).parseClaimsJws(jwt).getBody().getSubject();
//        try {
//            return objectMapper.readValue(subject, Subject.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 获取私钥
//     *
//     * @return 私钥
//     */
//    private SecretKey key() {
////        com.liaoin.service.core.com.liaoin.service.controller.log.error(new String(Base64Utils.encode(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded())));
//        return Keys.hmacShaKeyFor(Base64Utils.encode(key.getBytes()));
//    }
//
//    @Getter
//    @Setter
//    public static class Subject {
//
//        /**
//         * 用户ID
//         */
//        private String userId;
//
//        /**
//         * 组织ID
//         */
//        private String organizationId;
//
//        /**
//         * 组织类型
//         */
//        private OrganizationType organizationType;
//
//
//        /**
//         * 部门ID
//         */
//        private String departmentId;
//    }
//
//    @Getter
//    public class OrganizationType{
//        private String  SUPPLIER;
//
//    }
//}
