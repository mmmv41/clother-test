package com.microsoftwo.jwttest.email.config;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/* memo : Redis 활용 유틸 클래스
 *  - RedisTemplate<String, String>을 주입받아 Redis 데이터 접근 메서드 제공.
 *  - 이메일 인증 코드 저장, 조회, 만료 시간 설정, 삭제 기능 포함. */
@Service
@RequiredArgsConstructor
public class RedisUtil {
    private final RedisTemplate<String, String> redisTemplate; // Redis에 접근하기 위한 Spring의 Redis 템플릿 클래스

    // 데이터 조회
    public String getData(String key) { // 지정된 키(key)에 해당하는 데이터를 Redis에서 가져오는 메서드
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    // 데이터 저장
    public void setData(String key, String value) { // 지정된 키(key)에 값을 저장하는 메서드
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    // 일정 시간 후 만료되는 데이터 저장
    public void setDataExpire(String key, String value,
                              long duration) { // 지정된 키(key)에 값을 저장하고, 지정된 시간(duration) 후에 데이터가 만료되도록 설정하는 메서드
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofMinutes(duration);
        valueOperations.set(key, value, expireDuration);
    }

    // 데이터 삭제
    public void deleteData(String key) { // 지정된 키(key)에 해당하는 데이터를 Redis에서 삭제하는 메서드
        redisTemplate.delete(key);
    }

    // 키 존재 여부 확인 (지금은 키가 이메일로 되어있음)
    public boolean exists(@Email @NotEmpty(message = "이메일을 입력해 주세요") String email) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(email));
    }

//    // 키 존재 여부 확인
//    public boolean exists(String key) {
//        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
//    }
}
