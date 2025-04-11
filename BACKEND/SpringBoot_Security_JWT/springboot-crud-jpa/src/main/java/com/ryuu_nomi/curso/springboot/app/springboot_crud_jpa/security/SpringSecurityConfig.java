package com.ryuu_nomi.curso.springboot.app.springboot_crud_jpa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http.authorizeHttpRequests( (authz) -> authz
                    .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                    .anyRequest()
                    .authenticated()
            )
            .csrf(config -> config.disable()
            )
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

        // return http.authorizeHttpRequests(authz -> authz
        //         .anyRequest().permitAll())
        //         .csrf(csrf -> csrf.disable())
        //         .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        //         .build();
    //}
}
