package com.calendar.milestone.security.filter;


import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;
import com.calendar.milestone.security.token.JwtToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.web.AuthenticationEntryPoint;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    final AuthenticationManager authenticationManager;
    final AuthenticationEntryPoint authenticationEntryPoint;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
            AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationManager = authenticationManager;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        final String clientToken = request.getHeader("Authorization");
        if (clientToken == null || !clientToken.startsWith("Bearer ")) {// Bearer認証であることの確認
            filterChain.doFilter(request, response);
            return;
        }
        JwtToken token = JwtToken.fromClientToken(clientToken);// Bearerの除去
        BearerTokenAuthenticationToken bearerToken =
                new BearerTokenAuthenticationToken(token.getToken());
        try {
            // 認証済みユーザーをsecurityContextにset
            final Authentication authentication = authenticationManager.authenticate(bearerToken);
            final SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
        } catch (AuthenticationException authenticationException) {
            SecurityContextHolder.clearContext();
            authenticationEntryPoint.commence(request, response, authenticationException);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
