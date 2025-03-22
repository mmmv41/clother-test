//package com.microsoftwo.jwttest.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
/// * memo : 스프링 시큐리티의 인가 및 설정을 담당하는 클래스.
// *   Security Config 구현은 스프링 시큐리티의 세부 버전별로 상이하다  */
//
//@Configuration
//@EnableWebSecurity // security 클래스 임을 알림
//public class SecurityConfig {
//    // security filter chain 메서드
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        /* memo : 세션 방식에서는 세션이 고정되므로, csrf 공격을 방어해줘야함.
//         *   jwt 무상태성으로 관리하므로, csrf 공격을 관리하지 않아도됨. */
//
//        // csrf disable 설정 (사이트 위변조 방지 설정 (스프링 시큐리티에는 자동으로 설정 되어 있음))
//        http
//                .csrf((auth) -> auth.disable());
//
//        // form 로그인 방식 disable 설정
//        http
//                .formLogin((auth) -> auth.disable());
//
//        // 경로별 인가 작업 (허용되는 경로 및 권한 설정)
//        http
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/login", "/", "/join").permitAll()
//                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .anyRequest().authenticated());
//        // ROLE_ADMIN 인지 판단(권한이 있는지) -> 인가 절차, member 권한도 주려면 hasAnyRole("ADMIN", "MEMBER")
//
//        // 세션 설정
//        http
//                .sessionManagement((session) -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
////        // 새로 만든 로그인 필터를 원래의 (UsernamePasswordAuthenticationFilter)의 자리에 넣음
////        http
////                .addFilterAt(new LoginFilter(authenticationManager(configuration), jwtUtil),
////                        UsernamePasswordAuthenticationFilter.class);
////
////        // 로그인 필터 이전에 JWTFilter를 넣음
////        http
////                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
//
//        return http.build();
//    }
//}
//
//
//
//
//
//
