package com.oekrem.SpringMVCBackEnd.email;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EMailServiceIntegrationTest {

    @Autowired
    private EMailService emailService;

    @Test
    public void testSendEmail() {
        try{
            EMailRequest eMailRequest = EMailRequest.builder()
                    .to("ekrem995@hotmail.com")
                    .content("Hello World!")
                    .subject("Hello World! Content")
                    .build();
            emailService.sendSimpleEmail(eMailRequest);
            System.out.println("Email sent successfully");
        }catch (Exception e) {
            System.out.println("Mail gönderilirken hata oluştu");
        }
    }
}
