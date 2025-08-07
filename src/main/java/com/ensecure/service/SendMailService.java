package com.ensecure.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

    private final AmazonSimpleEmailService sesClient;
    
    public SendMailService(AmazonSimpleEmailService sesClient) {
    	this.sesClient = sesClient;
    }

    // 발신자
    @Value("${aws.ses.send-mail-to}")
    private String from;

    // 제목
    static final String SUBJECT = "Amazon SES test";

    static final String HTMLBODY = "<h1>Amazon SES test</h1>"
        + "<p>AWS SES 메일 테스트 입니다.</a>";

    public void sendMail(String to) {
        try {
            SendEmailRequest request = new SendEmailRequest()
                .withDestination(
                    new Destination().withToAddresses(to))
                .withMessage(new Message()
                    .withBody(new Body()
                        .withHtml(new Content()
                            .withCharset("UTF-8").withData(HTMLBODY)))
                    .withSubject(new Content()
                        .withCharset("UTF-8").withData(SUBJECT)))
                .withSource(from)
//                .withConfigurationSetName(CONFIGSET) // 선택 사항 (예: 구성 세트 사용)
                ;

            sesClient.sendEmail(request);

            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                + ex.getMessage());
        }
    }
}
