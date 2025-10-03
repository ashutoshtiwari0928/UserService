package com.paypal.UserService.Util;

import com.paypal.UserService.DTO.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;


@Component
public class JWTConfig {
    private String secretString = System.getenv().get("SECRET");

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
    }

    public String generate(UserDTO userDTO){
        return Jwts.builder()
                .claim("name",userDTO.getName())
                .claim("email",userDTO.getEmail())
                .signWith(getSecretKey())
                .compact();
    }

    public String extractName(String jws){
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jws)
                .getPayload()
                .get("name")
                .toString();
    }
    public String extractEmail(String jws){
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jws)
                .getPayload()
                .get("email")
                .toString();
    }
    public boolean validateToken(String jws){
        String email = extractEmail(jws);
        String name = extractName(jws);
        if(email==null || name==null)return false;
        String newJws = generate(new UserDTO(name,email));
        return jws.equals(newJws);
    }

}
