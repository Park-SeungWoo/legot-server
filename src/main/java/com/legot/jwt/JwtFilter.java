package com.legot.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String path = request.getRequestURI();

        if (path.contains("/users/sign")) {  // allow sign in, up without token
            chain.doFilter(request, response);
            return;
        }

        String accessToken = jwtProvider.resolveAccessToken(request);
        boolean isTokenValid = jwtProvider.validateToken(accessToken);

        if (isTokenValid) setAuthentication(accessToken);

        chain.doFilter(request, response);
    }

    private void setAuthentication(String token) {
        Authentication authentication = jwtProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
