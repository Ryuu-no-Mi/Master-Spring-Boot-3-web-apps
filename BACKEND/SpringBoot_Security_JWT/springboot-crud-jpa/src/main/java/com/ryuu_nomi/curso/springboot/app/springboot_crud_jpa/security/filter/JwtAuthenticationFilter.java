package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.security.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        User user = null;
        String username = null;
        String password = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            username = user.getUsername();
            password = user.getPassword();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);

        return authenticationManager.authenticate(authenticationToken);
    }


    //Si todo sale bien en el login
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        //asi se genera el token:
        org.springframework.security.core.userdetails.User userDetail = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        String username = userDetail.getUsername();
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
        
        //Claims da error al crear un objeto inmutable
        //Claims claims = Jwts.claims().build();
        //claims.put("authorities", roles);

        /*
         * //Claims claims = Jwts.claims()
         * // .add("authorities", new ObjectMapper().writeValueAsString(roles))
         * // .add("username", username)
         * // .build();
         * Eso hace que en el token tengas esto:
         * "authorities": "[{\"authority\":\"ROLE_USER\"}]"
         * ⚠️ Spring Security no interpreta eso como una lista de roles válidos
         * automáticamente,
         * y no bloquea al usuario como debería.
         */

        /*
        ✅ SOLUCIÓN
            Debes guardar los roles como una lista real de strings, no como un string JSON:
        */

        Claims claims = Jwts.claims()
                .add("authorities", roles.stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()) // ✔️ ahora es una lista de strings
                .add("username", username)
                .build();

        //Map<String, Object> claims = new HashMap<>();
        //claims.put("authorities", roles);

        String token = Jwts.builder()
                .subject(username)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();

        //response.addHeader("Authorization", "Bearer " + token);

        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("username", username);
        body.put("message", String.format("Hola %s has iniciado sesion con exito", username));

        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        
        Map<String, String> body = new HashMap<>();
        body.put("message", "Error en la autentificacion username o password incorrectos!!");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType(CONTENT_TYPE);
    }
    

}
