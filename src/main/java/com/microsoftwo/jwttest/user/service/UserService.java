package com.microsoftwo.jwttest.user.service;

import com.microsoftwo.jwttest.email.exception.CustomException;
import com.microsoftwo.jwttest.user.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

// UserDetailsService 를 상속받으면 자동으로 Bean 으로 등록
public interface UserService extends UserDetailsService {

    String registUser(UserDTO userDTO) throws CustomException;
}
