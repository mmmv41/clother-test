//package com.microsoftwo.jwttest.security2.controller;
//
//import com.microsoftwo.jwttest.security2.service.AuthService;
//import com.microsoftwo.jwttest.security2.vo.LoginRequestVO;
//import com.microsoftwo.jwttest.security2.vo.TokenDTO;
//import com.microsoftwo.jwttest.security2.vo.TokenRequestVO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//public class AuthController {
//    private final AuthService authService;
//
//    @PostMapping("/login")
//    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequestVO loginRequestVO) {
//        return ResponseEntity.ok(authService.login(loginRequestVO));
//    }
//
//    @PostMapping("/reissue")
//    public ResponseEntity<TokenDTO> reissue(@RequestBody TokenRequestVO tokenRequestVO) {
//        return ResponseEntity.ok(authService.reissue(tokenRequestVO));
//    }
//}