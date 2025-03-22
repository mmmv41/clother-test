package com.microsoftwo.jwttest.email.service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    String joinEmail(@Email @NotEmpty(message = "이메일을 입력해 주세요") String email);

    boolean CheckAuthNum(@Email @NotEmpty(message = "이메일을 입력해 주세요") String email,
                         @NotEmpty(message = "인증 번호를 입력해 주세요") String authNum);
}