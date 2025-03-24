package com.microsoftwo.jwttest.user.vo;

import lombok.Data;

@Data
public class SignupResponseVO {
    private String email;
    private String name;
    private String userId;
}

