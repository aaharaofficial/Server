package com.atman.aahara.Notification;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;
import org.springframework.scheduling.annotation.Async;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final SesClient sesClient;

    @Value("${notification.email.sender:no-reply@yourdomain.com}")
    private String senderEmail;

    /**
     * Send a plain text email asynchronously.
     */
    @Async
    public void sendEmail(String toEmail, String subject, String body) {
        Message message = buildMessage(subject, body);
        SendEmailRequest request = SendEmailRequest.builder()
                .source(senderEmail)
                .destination(Destination.builder().toAddresses(toEmail).build())
                .message(message)
                .build();

        try {
            sesClient.sendEmail(request);
        } catch (SesException e) {
            System.err.println("Failed to send email: " + e.awsErrorDetails().errorMessage());
            throw e;
        }
    }

    private Message buildMessage(String subject, String bodyText) {
        Content subjectContent = Content.builder().data(subject).build();
        Content bodyContent = Content.builder().data(bodyText).build();
        Body body = Body.builder().text(bodyContent).build();

        return Message.builder()
                .subject(subjectContent)
                .body(body)
                .build();
    }
}
