//package com.microsoftwo.jwttest.config.signIn;
//
/// * memo : Redis Server에 데이터를 생성하고 삭제하고 인증번호를 만드는 기능이 있는 Util 클래스 */
//
//import java.time.Duration;
//import java.util.Random;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class RedisUtil {
//    /* memo : Spring Redis Data 에서 제공하는 클래스. RedisTemplate<String, String> 클래스를 상속 받음.  */
//    private final StringRedisTemplate template;
//
//    @Value("${spring.data.redis.duration}")
//    private int duration;
//
//    public String getData(String key) {
//        ValueOperations<String, String> valueOperations = template.opsForValue();
//        return valueOperations.get(key);
//    }
//
//    public boolean existData(String key) {
//        return Boolean.TRUE.equals(template.hasKey(key));
//    }
//
//    /* memo : Redis Server에 데이터를 인입하며, application.yml에 설정한 duration 값을 이용하여 10분 후에 데이터가 삭제되게 설정. */
//    public void setDataExpire(String key, String value) {
//        ValueOperations<String, String> valueOperations = template.opsForValue();
//        Duration expireDuration = Duration.ofSeconds(duration);
//        valueOperations.set(key, value, expireDuration);
//    }
//
//    public void deleteData(String key) {
//        template.delete(key);
//    }
//
//    public void createRedisData(String toEmail, String code) {
//        if (existData(toEmail)) {
//            deleteData(toEmail);
//        }
//
//        setDataExpire(toEmail, code);
//    }
//
//    /* memo : 숫자, 영문자를 합친 6자리의 랜덤 인증 번호 생성 */
//    public String createdCertifyNum() {
//        int leftLimit = 48; // number '0'
//        int rightLimit = 122; // alphabet 'z'
//        int targetStringLength = 6;
//        Random random = new Random();
//
//        return random.ints(leftLimit, rightLimit + 1)
//                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
//                .limit(targetStringLength)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString();
//    }
//}
