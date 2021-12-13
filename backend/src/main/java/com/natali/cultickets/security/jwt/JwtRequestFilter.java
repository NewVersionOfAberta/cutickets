package com.natali.cultickets.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith(TOKEN_PREFIX)) {
            jwtToken = requestTokenHeader.substring(TOKEN_PREFIX.length());
            try {
                username = this.jwtUtils.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                log.error("Illegal JWT Token {}", jwtToken, e);
            } catch (ExpiredJwtException e) {
                log.error("JWT Token {} has expired", jwtToken, e);
            }
        } else {
            log.warn("JWT Token does not begin with Bearer String");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Authentication authentication = this.jwtUtils.generateAuthentication(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

}
