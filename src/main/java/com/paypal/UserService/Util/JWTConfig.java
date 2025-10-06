package com.paypal.UserService.Util;

import com.paypal.UserService.DTO.UserDTO;
import com.paypal.UserService.Entity.role;
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

    public String generate(UserDTO userDTO,role r){
        return Jwts.builder()
                .claim("name",userDTO.getName())
                .claim("email",userDTO.getEmail())
                .claim("role",r.toString())
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
    public role extractRole(String jws){
        return
                role.valueOf(
                        Jwts
                        .parser()
                        .verifyWith(getSecretKey())
                        .build()
                        .parseSignedClaims(jws)
                        .getPayload()
                        .get("role")
                        .toString()
                );
    }
    public boolean validateToken(String jws){
        String email = extractEmail(jws);
        String name = extractName(jws);
        role role = extractRole(jws);
        if(email==null || name==null || role==null)return false;
        String newJws = generate(new UserDTO(name,email),role);
        return jws.equals(newJws);
    }

}
