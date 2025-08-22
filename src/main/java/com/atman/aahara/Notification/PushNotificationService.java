package com.atman.aahara.Notification;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class PushNotificationService {


    @Async
    public void sendPushNotification(String deviceToken, String title, String body, Map<String, String> data) {
        try {
            Message message = Message.builder()
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
//
//            String response = FirebaseMessaging.getInstance().send(message);
//            log.info("Push notification sent. Response: {}", response);
        } catch (Exception e) {
            log.error("Failed to send push notification to {}: {}", deviceToken, e.getMessage(), e);
        }
    }
}
