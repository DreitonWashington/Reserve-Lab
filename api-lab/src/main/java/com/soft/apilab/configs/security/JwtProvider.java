package com.soft.apilab.configs.security;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Component
public class JwtProvider {

    @Value("${jwtSecret}")
    private String jwtSecret;
    @Value("${jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwt(Authentication authentication){
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userPrincipal.getUsername());
        claims.put("email", userPrincipal.getEmail());

        return Jwts.builder()
                .setSubject((userPrincipal.getUserId().toString())).addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getSubjectJwt(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwt(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch(SignatureException e){
            log.error("Invalid JWT signature: {}", e.getMessage());
        }catch(MalformedJwtException e){
            log.error("Invalid JWT token: {}", e.getMessage());
        }catch(ExpiredJwtException e){
            log.error("JWT token is expired: {}", e.getMessage());
        }catch(UnsupportedJwtException e){
            log.error("JWT token is unsupported: {}", e.getMessage());
        }catch(IllegalArgumentException e){
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
