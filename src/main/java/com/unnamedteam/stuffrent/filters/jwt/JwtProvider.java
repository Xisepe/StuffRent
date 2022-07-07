package com.unnamedteam.stuffrent.filters.jwt;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import static com.unnamedteam.stuffrent.constants.SecurityConstants.JWT_SECRET;

@Component
@Log
public class JwtProvider {

    public String generateToken(String username) {
        Date date = Date.from(
                LocalDate.now()
                .plusDays(15)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
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
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
