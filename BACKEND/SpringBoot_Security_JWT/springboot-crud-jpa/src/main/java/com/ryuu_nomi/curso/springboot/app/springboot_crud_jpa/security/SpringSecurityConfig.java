package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.security;

import java.util.Arrays;

import org.springframework.web.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.security.filter.JwtAuthenticationFilter;
import com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.security.filter.JwtValidationFIilter;

/*
 * CORS es un mecanismo de seguridad del navegador que controla qué servidores (orígenes) 
 * pueden hacer peticiones al backend (por ejemplo: desde http://localhost:3000 al backend en http://localhost:8080).
 */
//@CrossOrigin(origins = "http://localhost:4200") //Puerto de angular
//@CrossOrigin(originPatterns = "http://localhost:")
@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")

@Configuration
//@EnableGlobalMethodSecurity deprecated
@EnableMethodSecurity(prePostEnabled = true)

public class SpringSecurityConfig {

    //configurar el authenticationConfiguration;
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authz) -> authz
                .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/products", "/api/products/**").permitAll() // <-- Para poder visualizar los productos sin autenticación
                .requestMatchers(HttpMethod.POST, "/api/products", "/api/products/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/products", "/api/products/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/products", "/api/products/**").permitAll()
                // .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                // .requestMatchers(HttpMethod.GET, "/api/products", "/api/products/{id}").hasAnyRole("ADMIN","USER")
                // .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
                // .requestMatchers(HttpMethod.PUT, "/api/products/{id}").hasRole("ADMIN")
                // .requestMatchers(HttpMethod.DELETE, "/api/products/{id}").hasRole("ADMIN")
                .anyRequest().authenticated())
                //.addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFIilter(authenticationManager()))
                .csrf(config -> config.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

        // return http
        //         .csrf(csrf -> csrf.disable())
        //         .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        //         .authorizeHttpRequests(authz -> authz
        //                 .requestMatchers("/api/users", "/api/users/**").permitAll()
        //                 .requestMatchers("/api/products", "/api/products/**").permitAll()
        //.anyRequest().permitAll() // ⚠️ mientras debuggeas, luego cámbialo a authenticated()
        //                 .anyRequest().authenticated() // ⚠️ mientras debuggeas, luego cámbialo a authenticated()
        //         )
        //         .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
                new org.springframework.web.filter.CorsFilter(corsConfigurationSource()));

        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return corsBean;
    }

}
