package com.microsoftwo.jwttest.email.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/* memo : Redis 데이터베이스에 연결하고 RedisTemplate을 사용할 수 있도록 빈을 등록 */
/* memo : Redis 설정 클래스 */
@Configuration
public class RedisConfig {
    // 명시적으로 값 주입 (사용하지 않아도 boot 가 자동으로 적용해줌)
    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    // IoC container를 통해 lettuce connector 설정
    // PersistenceExceptionTranslator 역할을 수행
    // memo : yml파일에 기입한 host, port 로 redis server 와 연결한 lettuce 클라이언트 객체 생성
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 직렬화 설정 (Key와 Value를 문자열로 低張)
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }
}
