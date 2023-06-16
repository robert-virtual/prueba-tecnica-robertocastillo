package com.example.securityservice.service;

import com.example.securityservice.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${app.jwt.access.secret}")
    private String ACCESS_TOKEN_SECRET;
    @Value("${app.jwt.access.expiration}")
    private String ACCESS_TOKEN_EXPIRATION_MILLIS;


    public String generateToken(User userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> payload, User userDetails) {
        // 10 minutes = 1000*60*10 =
        Date expirationDate = new Date(System.currentTimeMillis() + Long.parseLong(ACCESS_TOKEN_EXPIRATION_MILLIS));
        return Jwts
                .builder()
                .setSubject(userDetails.getId().toString())
                .setExpiration(expirationDate)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .addClaims(payload)
                .signWith(getSigningKey())
                .compact();
    }


    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(ACCESS_TOKEN_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claims != null
                ? claimsResolver.apply(claims)
                : null;
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception e) {
            return null;
        }
    }
}
