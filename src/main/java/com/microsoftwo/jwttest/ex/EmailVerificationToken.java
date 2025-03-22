//package com.microsoftwo.jwttest.config;
//
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
//import java.time.LocalDateTime;
//import lombok.Data;
//
/// / memo : 이메일 인증을 위한 토큰을 저장하는 엔티티
//@Data
//public class EmailVerificationToken {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String token;
//
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    private LocalDateTime expiryDate;
//}
