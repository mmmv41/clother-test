package com.microsoftwo.jwttest.user.vo;

import lombok.Data;

/* memo : 로그인 시 입력 받는 값 */
@Data
public class LoginRequestVO {
    private String email;
    private String pwd;
}
