package com.microsoftwo.jwttest.security2.vo;

import com.microsoftwo.jwttest.user.aggregate.UserEntity;
import lombok.Data;

@Data
public class LoginResponseVO {
    private String email;

    public static LoginResponseVO of(UserEntity userEntity) {
        return new LoginResponseVO();
    }
}
