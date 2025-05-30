package com.diego.productoservice.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro para controlar consumo de recursos, inicialmente cuenta con seguridad
 * el path api/productos, si no se cumple con la clave secreat retorna
 * unAuthorized
 * 
 * @author Diego Sanchez
 */
@Component
public class FiltroApiKey extends OncePerRequestFilter {
    private final String API_KEY = "123456";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String apiKey = request.getHeader("X-API-KEY");
        if ("/api/productos".equals(request.getRequestURI()) || API_KEY.equals(apiKey)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
        }
    }
}
