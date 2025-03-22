package com.microsoftwo.jwttest.email.controller;

import com.microsoftwo.jwttest.email.dto.EmailCheckDTO;
import com.microsoftwo.jwttest.email.service.EmailServiceImpl;
import com.microsoftwo.jwttest.email.vo.EmailRequestVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailController {
    private final EmailServiceImpl mailService;

    /* memo : 인증 코드 전송 */
    @PostMapping("/mailSend")
    public String mailSend(@RequestBody @Valid EmailRequestVO emailDto) {

        log.info("이메일 인증 요청 보냄");
        log.info("Sending email : {}", emailDto);
//        System.out.println("이메일 인증 요청이 들어옴");
//        System.out.println("이메일 인증 이메일 :" + emailDto.getEmail());
        return mailService.joinEmail(emailDto.getEmail());
    }

    /* memo : 인증 코드가 일치하는지 확인 */
    @PostMapping("/mailauthCheck")
    public String AuthCheck(@RequestBody @Valid EmailCheckDTO emailCheckDto) {
        boolean Checked = mailService.CheckAuthNum(emailCheckDto.getEmail(), emailCheckDto.getAuthNum());
        if (Checked) {
            return "인증 코드가 일치합니다.";
        } else {
            throw new NullPointerException("인증 코드가 일치하지 않습니다.");
        }
    }
}
