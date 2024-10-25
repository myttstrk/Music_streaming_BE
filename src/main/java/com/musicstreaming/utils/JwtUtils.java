package com.musicstreaming.utils;

import com.musicstreaming.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    // Tạo JWT token và bao gồm deviceId trong payload
    public String generateJwtToken(User user, String deviceId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("deviceId", deviceId);

        return Jwts.builder()
                .setClaims(claims)  // Đặt thêm thông tin vào payload
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Lấy deviceId từ token
    public String getDeviceIdFromJwtToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.get("deviceId", String.class);
    }
    public String getUserIdFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    // Xác thực token
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (JwtException  | IllegalArgumentException e) {
            System.out.println("JWT validation failed: " + e.getMessage());
        }
        return false;
    }

}
