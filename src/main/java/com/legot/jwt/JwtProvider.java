package com.legot.jwt;

import com.legot.enums.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final CustomUserDetailService customUserDetailService;

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.access-token-validity}")
    private long accessTokenValidTime;
    @Value("${jwt.refresh-token-validity}")
    private long refreshTokenValidTime;

    private String createToken(String email, UserRole role, Long tokenValidTime) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", role);
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        Date now = new Date();

        return "bearer " + Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createAccessToken(String email, UserRole role) {
        return this.createToken(email, role, accessTokenValidTime);
    }

    public String createRefreshToken(String email, UserRole role) {
        return this.createToken(email, role, refreshTokenValidTime);
    }

    private String resolveToken(HttpServletRequest request, String headerName) {
        String token = request.getHeader(headerName);
        if (token != null)
            return token.substring(7);
        else
            return null;
    }

    public String resolveAccessToken(HttpServletRequest request) {
        return resolveToken(request, "Authorization");
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        return resolveToken(request, "refresh-token");
    }

    public boolean validateToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT", e);
        } catch (ExpiredJwtException e) {
            throw new JwtException("JWT has expired", e);
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("JWT is unsupported", e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("JWT claims string is empty", e);
        } catch (SignatureException e) {
            throw new SignatureException("JWT signature verification failed");
        }
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token){
        UserDetails userDetails = customUserDetailService.loadUserByUsername(this.getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUserEmail(String token){
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token);
        return claims.getBody().getSubject();
    }
}
