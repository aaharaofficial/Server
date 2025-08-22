package com.atman.aahara.Notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class SmsNotificationService {

//    private final SnsClient snsClient;

    @Async
    public void sendSmsNotification(String mobileNumber, String message) {
        try {
            String formattedNumber = formatMobileNumber(mobileNumber);

            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .phoneNumber(formattedNumber)
                    .build();

//            PublishResponse result = snsClient.publish(request);
//            log.info("SMS sent to {} | MessageId: {}", formattedNumber, result.messageId());
//            result.sdkHttpResponse().isSuccessful();
        } catch (Exception e) {
            log.error(" Failed to send SMS to {}: {}", mobileNumber, e.getMessage(), e);
        }
    }

    private String formatMobileNumber(String mobileNumber) {
        if (!mobileNumber.startsWith("+")) {
            return "+91" + mobileNumber.trim();
        }
        return mobileNumber;
    }
}
