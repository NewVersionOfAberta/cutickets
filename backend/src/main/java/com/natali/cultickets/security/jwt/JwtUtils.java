package com.natali.cultickets.security.jwt;

import com.natali.cultickets.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils implements Serializable {
    private static final String ROLES_CLAIM = "ROLES";

    @Value("${jwt.token.secret}")
    private String jwtSecret;
    @Value("${jwt.token.expired}")
    private long expirationTime;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

//    public String generateToken(UserDto userDetails) {
////        List<GrantedAuthority> userAuthority = UserDetailsServiceImpl.getUserAuthority(userDetails.getRoles());
//
//        return doGenerateToken(userAuthority, userDetails.getEmail());
//    }

    private String doGenerateToken(List<GrantedAuthority> claims, String subject) {
        return Jwts.builder()
                .claim(ROLES_CLAIM, claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.expirationTime))
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Authentication generateAuthentication(final String jwtString) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.jwtSecret)
                .parseClaimsJws(jwtString)
                .getBody();

        String email = claims.getSubject();
        Collection<?> roles = claims.get(ROLES_CLAIM, Collection.class);
        List<GrantedAuthority> grantedAuthorities = roles.stream()
                .map((e) -> new SimpleGrantedAuthority(e.toString()))
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(email, null, grantedAuthorities);
    }
}
