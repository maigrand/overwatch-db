package com.maigrand.overwatchdb.security;

import com.maigrand.overwatchdb.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.security.jwt.secret}")
    private String secret;

    @Value("${app.security.jwt.expiration-time}")
    private long expirationTime;

    @Value("${app.security.jwt.remember-me-expiration-time}")
    private long rememberMeExpirationTime;

    public String generateToken(String email, boolean rememberMe) {
        Date nowDate = new Date();
        Date expiryDate = new Date(nowDate.getTime() +
                (rememberMe ? this.rememberMeExpirationTime : this.expirationTime));

        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(nowDate)
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public String generateToken(Authentication authentication, boolean rememberMe) {
        User user = (User) authentication.getPrincipal();

        return generateToken(user, rememberMe);
    }

    public String generateToken(User user, boolean rememberMe) {
        return generateToken(user.getEmail(), rememberMe);
    }

    public String generateToken(String email) {
        return generateToken(email, false);
    }

    public String generateToken(Authentication authentication) {
        return generateToken(authentication, false);
    }

    public String generateToken(User user) {
        return generateToken(user, false);
    }

    public String getUserEmailFromJWT(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Integer getPasswordHashFromJWT(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return (Integer) claims.get("passwordHash");
    }

    public boolean validateToken(String token) throws SignatureException {
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
