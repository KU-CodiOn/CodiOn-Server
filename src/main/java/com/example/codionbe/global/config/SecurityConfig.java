package com.example.codionbe.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/swagger-ui/**", "/v3/api-docs/**",
                                "/swagger-resources/**", "/swagger-ui.html", "/webjars/**",
                                "/v3/api-docs/swagger-config",
                                "/hello"
                        ).permitAll()
                        .requestMatchers("/auth/**", "/login", "/signup").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
