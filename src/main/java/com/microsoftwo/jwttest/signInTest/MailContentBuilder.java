//package com.microsoftwo.jwttest.config.signIn;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//
//
//@Service
//@RequiredArgsConstructor
//public class MailContentBuilder {
//
//    private final TemplateEngine templateEngine; // 템플릿 양식과 데이터를 합쳐서 html을 보여줌
//
//    public String build(String certifyNum) {
//        Context context = new Context();
//        context.setVariable("certifyNum", certifyNum); // html 파일의 ${certifyNum} 에 대한 값 매핑
//        return templateEngine.process("mailTemplate", context);
//    }
//}
