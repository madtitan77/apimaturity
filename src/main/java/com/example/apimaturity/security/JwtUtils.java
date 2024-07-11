package com.example.apimaturity.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


@Component
public class JwtUtils {
    
    private SecretKey jwtSecret;

    @Value("${app.jwtSecret}")
     public void setJwtSecret(String secretKeyString) {
        // Assuming the secret is Base64 encoded
        byte[] decodedKey = Base64.getDecoder().decode(secretKeyString);
        // Specify the algorithm you intend to use for the JWT
        this.jwtSecret = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }

    
    @Value("${app.jwtSecret}")
    private String Secret;   

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        //UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                //.header()
                .subject(authentication.getName())
                .issuedAt(now)
                .expiration(expiDate)
                .signWith(jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            //basic validation not parsing Claims
            Jwts.parser()
                    .verifyWith(jwtSecret)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
                    
            return true;
        } catch (JwtException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Token validation error: " + e.getMessage());
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(jwtSecret)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
}