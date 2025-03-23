package com.microsoftwo.jwttest.user.service;

import com.microsoftwo.jwttest.email.config.RedisUtil;
import com.microsoftwo.jwttest.email.exception.CustomException;
import com.microsoftwo.jwttest.user.aggregate.Role;
import com.microsoftwo.jwttest.user.aggregate.User;
import com.microsoftwo.jwttest.user.repository.UserRepository;
import com.microsoftwo.jwttest.user.vo.SignupRequestVO;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
    private final RedisUtil redisUtil;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                           ModelMapper modelMapper, RedisUtil redisUtil) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
        this.redisUtil = redisUtil;
    }


    // 기능 : 회원가입
    @Override
    public String registerUser(@Valid SignupRequestVO signupRequestVO) throws CustomException {
        // 이메일 중복 체크
        Optional<User> existingUser = userRepository.findByEmailOrNickname(signupRequestVO.getEmail(),
                signupRequestVO.getNickname());
        if (existingUser.isPresent()) {
            log.error("회원가입 실패 - 이미 존재하는 닉네임: {}", signupRequestVO.getNickname());
            throw new CustomException("이미 존재하는 닉네임입니다.");
        }

        // 이메일 인증 여부 확인
        if (!redisUtil.exists(signupRequestVO.getEmail())) {
            log.error("회원가입 실패 - 이메일 인증 필요: {}", signupRequestVO.getEmail());
            throw new CustomException("이메일 인증을 먼저 진행해 주세요.");
        }

        log.info("회원가입 진행 - 이메일: {}, 닉네임: {}", signupRequestVO.getEmail(), signupRequestVO.getNickname());

        // 회원 저장 로직...
        // DTO → Entity 변환 / 엔티티의 password 컬럼에 암호화 된 값을 추가
        User newUser = modelMapper.map(signupRequestVO, User.class);
        newUser.setPassword(bCryptPasswordEncoder.encode(signupRequestVO.getPassword())); // 비밀번호 암호화
        newUser.setRole(Role.USER);

        userRepository.save(newUser);
        redisUtil.deleteData(signupRequestVO.getEmail());

        return "회원가입이 완료되었습니다.";
    }

    /* memo : login 할때 자동 호출될 메소드 */
    /* 설명. spring security 사용 시 프로바이더에서 활요할 로그인용 메소드(id로 회원 조회해서 UserDetails 타입을 반환하는 메소드) */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final RedisUtil redisUtil;
//
//    @Transactional
//    public String registerUser(@Valid SignupRequestDTO request) throws CustomException {
//        // 이메일 또는 닉네임 중복 체크 (한 번의 SQL 실행)
//        Optional<User> existingUser = userRepository.findByEmailOrNickname(request.getEmail(), request.getNickname());
//        if (existingUser.isPresent()) {
//            if (existingUser.get().getEmail().equals(request.getEmail())) {
//                throw new CustomException("이미 가입된 이메일입니다.");
//            }
//            throw new CustomException("이미 사용 중인 닉네임입니다.");
//        }
//
//        // 이메일 인증 여부 확인
//        if (!redisUtil.exists(request.getEmail())) {
//            throw new CustomException("이메일 인증을 먼저 진행해 주세요.");
//        }
//
//        // 비밀번호 암호화
//        String encodedPassword = passwordEncoder.encode(request.getPassword());
//
//        // 회원 정보 저장
//        User newUser = User.builder()
//                .email(request.getEmail())
//                .password(encodedPassword)
//                .nickname(request.getNickname())
//                .gender(request.getGender())
//                .height(request.getHeight())
//                .weight(request.getWeight())
//                .role(Role.USER)
//                .build();
//
//        userRepository.save(newUser);
//
//        // 이메일 인증 정보 삭제
//        redisUtil.deleteData(request.getEmail());
//
//        return "회원가입이 완료되었습니다.";
//    }
}
