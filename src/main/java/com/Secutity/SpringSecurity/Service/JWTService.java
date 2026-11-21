
package com.Secutity.SpringSecurity.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

@Service
public class JWTService {

    private static final SecretKey SECRET_KEY =
            Jwts.SIG.HS256.key().build();

    public String generateToken(String name,String role) {
        HashMap<String,Object> claims = new HashMap<>();
        claims.put("Role" ,role);

        return Jwts.builder()
                .subject(name)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 30)
                )
                .addClaims(claims)
                .signWith(SECRET_KEY)
                .compact();
    }

    public Claims validAndExtractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractName(String token) {
        return validAndExtractAllClaims(token).getSubject();
    }

    public Date expirationDate(String token) {
        return validAndExtractAllClaims(token).getExpiration();
    }

    public boolean isExpired(String token) {
        return expirationDate(token).before(new Date());
    }
}
