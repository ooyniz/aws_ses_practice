package com.ensecure.controller;

import com.ensecure.service.SendMailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MailController {

    private final SendMailService sendMailService;
    
    public MailController(SendMailService sendMailService) {
    	this.sendMailService = sendMailService;
    }

    /**
     * 메일을 전송하는 엔드포인트입니다.
     * @param to 수신자 이메일 주소
     * @return "sendMail 호출" 메시지를 반환합니다.
     */
    @PostMapping("/send")
    public String sendMail(@RequestParam("to") String to) {
        sendMailService.sendMail(to);
        return "sendMail 호출";
    }
}
