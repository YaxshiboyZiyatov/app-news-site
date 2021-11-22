package com.example.appnewssite.security;



import com.example.appnewssite.entity.RoleLavozim;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {//tokenni generatsiya qiladi bu class

    private static final long expireTime = 1000 * 60 * 60 * 24;

    private static final String secretKey = "mahfiysuz";

    public String generateToken(String username, RoleLavozim roleLavozims) {
        Date expireData = new Date(System.currentTimeMillis() + expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireData)
                .claim("role", roleLavozims.getName())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return token;
    }

    public String getUsernameFromToken(String token){
        try {
            String email = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return email;
        }catch (Exception e){
            return null;
        }
    }


}
