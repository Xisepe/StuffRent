package com.unnamedteam.stuffrent.config.jwt;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Log
public class JwtProvider {
    private final String jwtSecret = "special";
    public String generateToken(String username) {
        Date date = Date.from(
                LocalDate.now()
                .plusDays(15)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.severe("Token expired");
        } catch (UnsupportedJwtException e) {
            log.severe("Unsupported jwt");
        } catch (MalformedJwtException e) {
            log.severe("Malformed jwt");
        } catch (SignatureException e) {
            log.severe("Invalid signature");
        } catch (Exception e) {
            log.severe("invalid token");
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
