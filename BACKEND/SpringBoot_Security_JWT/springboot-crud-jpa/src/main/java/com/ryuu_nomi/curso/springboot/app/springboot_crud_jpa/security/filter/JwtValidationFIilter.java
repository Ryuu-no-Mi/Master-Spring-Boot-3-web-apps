package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.security.filter;

import static com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.security.TokenJwtConfig.CONTENT_TYPE;
import static com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.security.TokenJwtConfig.PREFIX_TOKEN;
import static com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.security.TokenJwtConfig.SECRET_KEY;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFIilter extends BasicAuthenticationFilter {

    public JwtValidationFIilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(HEADER_AUTHORIZATION);

        if (header == null || !header.startsWith(PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(PREFIX_TOKEN, "");

        try {
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
            String username = claims.getSubject();
            //String username3 = (String) claims.get("username"); segudna opc
           // Object authoritiesClaims = claims.get("authorities");

            // Collection<? extends GrantedAuthority> authorities = Arrays.asList( 
            //         new ObjectMapper()
            //         .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
            //         .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));ç

            @SuppressWarnings("unchecked")
            List<String> roles = (List<String>) claims.get("authorities");

            Collection<? extends GrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                    null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            chain.doFilter(request, response);
        } catch (JwtException e) {

            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            body.put("message", "El token JWT es inválido!");

            response.setContentType(CONTENT_TYPE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));

        }

    }

}
