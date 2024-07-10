package com.example.apimaturity.security;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import com.example.apimaturity.service.UserDetailsServiceImpl; // Add this import statement
import org.springframework.security.core.Authentication; // Add this import statement
import org.springframework.security.core.userdetails.UserDetails;



@Component
public class JwtUtils {

    private String secretKey = "yourSecretKey"; 

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
    UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
    return Jwts.builder()
            .setSubject(userPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact(); 
    }

    public boolean validateJwtToken(String token) {
        try {
            // Parse the token. If it's malformed or the signature doesn't match, an exception will be thrown.
            Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();

            //TODO
            // Here, you can add additional validation as needed, such as checking the claims.
            // For simplicity, this example just returns true if the token is correctly parsed.
            
            return true;
        } catch (SignatureException e) {
            // Log the exception and return false if the token is invalid.
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (Exception e) {
            // Log other exceptions. You might want to handle different exceptions differently.
            System.out.println("Token validation error: " + e.getMessage());
        }
        return false;
    }


    public String getUserNameFromJwtToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}