package com.microsoftwo.jwttest.user.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String encryptedPwd; // 비밀번호는 암호화된 값만 저장
    private String nickname;
    private String gender;
    private int height;
    private int weight;
    private String role;
}
