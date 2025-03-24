package com.microsoftwo.jwttest.user.controller;

import com.microsoftwo.jwttest.security2.service.AuthService;
import com.microsoftwo.jwttest.security2.util.SecurityUtil;
import com.microsoftwo.jwttest.security2.vo.LoginRequestVO;
import com.microsoftwo.jwttest.security2.vo.LoginResponseVO;
import com.microsoftwo.jwttest.security2.vo.TokenDTO;
import com.microsoftwo.jwttest.user.service.UserService;
import com.microsoftwo.jwttest.user.vo.SignupRequestVO;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    // 기능 : 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestVO signupRequestVO,
                                          BindingResult bindingResult) {
        // 유효성 검사 실패 시 처리
        if (bindingResult.hasErrors()) {
            log.error("회원가입 요청 유효성 검사 실패: {}", signupRequestVO.getEmail());

            // 에러 메시지를 담을 Map 생성
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            // 로그 출력
            errors.forEach((field, message) -> log.error("유효성 오류 - {}: {}", field, message));

            return ResponseEntity.badRequest().body(errors);
        }

        // 회원가입 진행
        String result = userService.registerUser(signupRequestVO);
        return ResponseEntity.ok(result);
    }

    // 기능 : 로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequestVO loginRequestVO) {
        return ResponseEntity.ok(authService.login(loginRequestVO));
    }

    @GetMapping("/me")
    public ResponseEntity<LoginResponseVO> findMemberInfoById() {
        return ResponseEntity.ok(userService.findMemberInfoById(SecurityUtil.getCurrentMemberId()));
    }

    @GetMapping("/{email}")
    public ResponseEntity<LoginResponseVO> findMemberInfoByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findMemberInfoByEmail(email));
    }
}

//public class UserController {
//
//    private final UserService userService;
//    private final ModelMapper modelMapper;
//
//    // 회원가입
//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestVO request) throws CustomException {
//        UserDTO userDTO = modelMapper.map(request, UserDTO.class);
//        userService.registUser(userDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
//    }
//}

