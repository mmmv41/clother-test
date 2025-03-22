//package com.microsoftwo.jwttest.config.signIn;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MailService {
//
//    private final JavaMailSender javaMailSender;
//    private final MailDao mailDao;
//
//    private final RedisUtil redisUtil; //redis 관련
//
//    @Autowired
//    public MailService(JavaMailSender javaMailSender, MailDao mailDao, RedisUtil redisUtil) {
//        this.javaMailSender = javaMailSender;
//        this.mailDao = mailDao;
//        this.redisUtil = redisUtil;
//    }
//
//
//    private MimeMessage createMessage(String code, String email) throws Exception {
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        message.addRecipients(Message.RecipientType.TO, email);
//        message.setSubject("Planet 인증 번호입니다.");
//        message.setText("이메일 인증코드: " + code);
//
//        message.setFrom("환경변수로 등록한 네이버 아이디@naver.com"); //보내는사람.
//
//        return message;
//    }
//
//    public void sendMail(String code, String email) throws Exception {
//        try {
//            MimeMessage mimeMessage = createMessage(code, email);
//            javaMailSender.send(mimeMessage);
//        } catch (MailException mailException) {
//            mailException.printStackTrace();
//            throw new IllegalAccessException();
//        }
//    }
//
//    public String sendCertificationMail(String email) throws BaseException {
/// /        if(userProvider.checkEmail(email) == 1){ /            throw new
/// BaseException(BaseResponseStatus.DUPLICATED_EMAIL); /        }
//        try {
//            String code = UUID.randomUUID().toString().substring(0, 6); //랜덤 인증번호 uuid를 이용!
//            sendMail(code, email);
//
//            redisUtil.setDataExpire(code, email, 60 * 5L); // {key,value} 5분동안 저장.
//
//            return code;
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
//        }
//    }
//}