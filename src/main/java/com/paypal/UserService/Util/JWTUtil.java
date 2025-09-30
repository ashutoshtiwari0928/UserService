package com.paypal.UserService.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;
import java.util.Map;

@Component
public class JWTUtil {
    private static final String SECRET_KEY = "secret123secter123sectet123";

    private SecretKey getSignedKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    public String extractEmail(String token){
        return Jwts.parser()
                .verifyWith(getSignedKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public boolean validateToken(String token){
        try{
            extractEmail(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public String extractUsername(String token){
        return Jwts.parser()
                .verifyWith(getSignedKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public String generateToken(Map<String,Object> claims,String email){
        return Jwts
                .builder()
                .signWith(getSignedKey())
                .subject((String)claims.get("subject"))
                .compact();
    }
}
