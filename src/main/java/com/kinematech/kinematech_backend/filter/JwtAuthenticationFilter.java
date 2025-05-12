package com.kinematech.kinematech_backend.filter;

import com.kinematech.kinematech_backend.service.UserService;
import com.kinematech.kinematech_backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
                // Define public paths that do not require authentication
                String[] publicPaths = {
                    "/swagger-ui",
                    "/v3/api-docs",
                    "/swagger-ui.html",
                    "/api/users/register",
                    "/api/users/verify-email",
                    "/api/users/login",
                };

                // Check if the request path matches any public path
                for (String publicPath : publicPaths) {
                    if (path.equals(publicPath) || path.startsWith(publicPath)) {
                    filterChain.doFilter(request, response);
                    return;
                    }
                }

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return;
        }

        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

        try {
            String username = JwtUtils.getUsernameFromToken(token);
            String salt = userService.findByEmail(username).getSalt();

            if (!JwtUtils.validateToken(token, salt)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired token");
                return;
            }

            // Token is valid, proceed with the request
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authentication failed");
        }
    }
}