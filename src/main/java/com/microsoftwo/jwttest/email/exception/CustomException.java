package com.microsoftwo.jwttest.email.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message); // 부모 클래스(RuntimeException)의 생성자에 메시지 전달
    }
}
