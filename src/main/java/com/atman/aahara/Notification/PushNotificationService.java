package com.atman.aahara.Notification;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PushNotificationService {

    private final FirebaseMessaging firebaseMessaging;

    /**
     * Sends a push notification asynchronously.
     *
     * @param deviceToken Target device token
     * @param title       Notification title
     * @param body        Notification body
     * @param data        Additional key-value data
     */
    @Async
    public void sendPushNotification(String deviceToken, String title, String body, Map<String, String> data) {
        try {
            Message message = buildMessage(deviceToken, title, body, data);
            String response = firebaseMessaging.send(message);
            log.info("Push notification sent successfully. Response: {}", response);
        } catch (Exception e) {
            log.error("Failed to send push notification to {}: {}", deviceToken, e.getMessage(), e);
        }
    }

    /**
     * Builds a Firebase push message for Android devices.
     */
    private Message buildMessage(String deviceToken, String title, String body, Map<String, String> data) {
        return Message.builder()
                .setToken(deviceToken)
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .putAllData(data)
                .setAndroidConfig(AndroidConfig.builder()
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .build())
                .build();
    }
}
