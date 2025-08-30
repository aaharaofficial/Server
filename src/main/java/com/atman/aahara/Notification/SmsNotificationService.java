package com.atman.aahara.Notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsNotificationService {

    private final SnsClient snsClient;

    /**
     * Sends an SMS asynchronously using AWS SNS.
     *
     * @param mobileNumber Recipient mobile number
     * @param message      SMS content
     */
    @Async
    public void sendSmsNotification(String mobileNumber, String message) {
        try {
            String formattedNumber = formatMobileNumber(mobileNumber);

            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .phoneNumber(formattedNumber)
                    .build();

            PublishResponse result = snsClient.publish(request);
            log.info("SMS sent to {} | MessageId: {}", formattedNumber, result.messageId());
        } catch (Exception e) {
            log.error("Failed to send SMS to {}: {}", mobileNumber, e.getMessage(), e);
        }
    }

    /**
     * Formats the mobile number to include country code if missing.
     */
    private String formatMobileNumber(String mobileNumber) {
        String trimmedNumber = mobileNumber.trim();
        if (!trimmedNumber.startsWith("+")) {
            return "+91" + trimmedNumber; // Default to India country code
        }
        return trimmedNumber;
    }
}
