package br.com.catolica.crud_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // desativa CSRF para testes com Postman
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // permite todos os endpoints sem autenticação
                );

        return http.build();
    }
}
