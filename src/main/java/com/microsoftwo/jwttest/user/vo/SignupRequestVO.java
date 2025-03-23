package com.microsoftwo.jwttest.user.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/* memo : 사용자의 회원가입 요청 데이터를 받을 때  */
@Data
public class SignupRequestVO {

    @Email
    @NotEmpty(message = "이메일을 입력해 주세요.")
    private String email;

    @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]).+$",
            message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다")
    private String password;

    @NotEmpty(message = "닉네임을 입력해 주세요")
    private String nickname;

    // @Pattern(regexp = "정규식") 어노테이션은 입력값이 특정 정규식 패턴과 일치하는지 검증하는 데 사용
    @Pattern(regexp = "M|F", message = "M 또는 F만 입력 가능합니다.")
    private String gender;

    private int height;

    private int weight;
}