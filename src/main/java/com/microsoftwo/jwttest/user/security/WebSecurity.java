package com.microsoftwo.jwttest.user.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/mailSend", "/mailauthCheck", "/auth/signup").permitAll() // 회원가입 허용
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()); // CSRF 비활성화 (테스트용)

        return http.build();
    }
}
