package com.microsoftwo.jwttest.user.controller;

import com.microsoftwo.jwttest.email.exception.CustomException;
import com.microsoftwo.jwttest.user.dto.SignupRequestVO;
import com.microsoftwo.jwttest.user.dto.UserDTO;
import com.microsoftwo.jwttest.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestVO request) throws CustomException {
        UserDTO userDTO = modelMapper.map(request, UserDTO.class);
        userService.registUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
    }
}

