package com.microsoftwo.jwttest.security2.vo;

import lombok.Data;

@Data
public class TokenRequestVO {
    private String accessToken;
    private String refreshToken;
}
