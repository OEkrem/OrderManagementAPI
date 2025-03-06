package com.oekrem.SpringMVCBackEnd.email;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EMailServiceUnitTest {

    @Mock
    private JavaMailSender emailSender;

    @InjectMocks
    private EMailService emailService;

    @Test
    void testSendSimpleEmail() {
        // Arrange
        EMailRequest emailRequest = new EMailRequest("test@example.com", "Test Subject", "Test Content");

        // Act
        emailService.sendSimpleEmail(emailRequest);

        // Assert
        ArgumentCaptor<SimpleMailMessage> mailCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(emailSender, times(1)).send(mailCaptor.capture());

        SimpleMailMessage sentMessage = mailCaptor.getValue();
        assertEquals("no-reply@mailtrap.io", sentMessage.getFrom());
        assertEquals("ekrem995@hotmail.com", sentMessage.getTo()[0]);
        assertEquals("Test Subject", sentMessage.getSubject());
        assertEquals("Test Content", sentMessage.getText());
    }
}
