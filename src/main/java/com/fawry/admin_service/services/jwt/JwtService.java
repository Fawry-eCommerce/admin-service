package com.fawry.admin_service.services.jwt;

import com.fawry.admin_service.services.admin.AdminService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${security.security-key}")
    private String SECRET_KEY;
    private final AdminService adminService;

    public String extractByEmail(String token){
        return extractClaim(token , Claims::getSubject);
    }

    public String generateToken(UserDetails user){
        Map<String, Object> claims = new HashMap<>();
        String role = adminService.getAdminByEmail(user.getUsername()).getRole().name();
        claims.put("role", role);
        return generateToken(claims, user.getUsername());
    }

    public String generateToken(Map<String , Object> extraClaims , String email){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 24))
                .signWith(getSingingKey() , SignatureAlgorithm.HS256) // sign key to create signature
                .compact();
    }

    public boolean isTokenValid(String token , UserDetails userDetails){
        String userEmail = extractByEmail(token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = extractClaim(token , Claims::getExpiration);
        return expirationDate.before(new Date());
    }


    public <T> T extractClaim (String jwt , Function<Claims , T> extractResolver){
        Claims claims = extractAllClaims(jwt);
        return extractResolver.apply(claims);
    }

    public Claims extractAllClaims(String jwt){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSingingKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    public boolean hasRole(String token, String role) {
        Claims claims = extractAllClaims(token);
        String emails = claims.get("sub", String.class);
        String adminRole = adminService.getAdminByEmail(emails).getRole().name();
        return adminRole.equals(role);
    }

    public boolean hasAnyRole(String token, String... roles) {
        Claims claims = extractAllClaims(token);
        String email = claims.get("sub", String.class);
        String adminRole = adminService.getAdminByEmail(email).getRole().name();
        for (String role : roles) {
            if (adminRole.equals(role)) {
                return true;
            }
        }
        return false;
    }

    private Key getSingingKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
