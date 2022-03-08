package br.com.erp.configuration;

import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class CorsPreflightFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (CorsUtils.isPreFlightRequest(request)) {
            response.addHeader("Access-Control-Allow-Private-Network", "true");
        }

        filterChain.doFilter(request, response);
    }
}