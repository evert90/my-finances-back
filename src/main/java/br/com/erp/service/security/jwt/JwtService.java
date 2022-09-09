package br.com.erp.service.security.jwt;

import br.com.erp.bean.user.UserReadonly;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class JwtService {

    @Value("${app.auth.jwtSecret}")
    private String jwtSecret;

    @Value("${app.auth.jwtExpirationMs}")
    private Integer jwtExpirationMs;

    public String generateFromUser(UserReadonly user) {
        return build(user);
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validate(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    private String build(UserReadonly userReadonly) {
        Claims claims = Jwts.claims().setSubject(userReadonly.email());
        claims.putAll(Map.of(
                "id", userReadonly.id(),
                "name", userReadonly.name(),
                "email", userReadonly.email()
        ));

        return Jwts.builder()
                .setSubject(userReadonly.email())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
