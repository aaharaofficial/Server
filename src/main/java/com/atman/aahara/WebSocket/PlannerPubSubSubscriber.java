package com.atman.aahara.WebSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlannerPubSubSubscriber implements MessageListener {

    private final PlannerHelper plannerHelper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String channel = new String(message.getChannel());
            String body = new String(message.getBody());
            PlannerStatusEvent event = objectMapper.readValue(body, PlannerStatusEvent.class);

            // Extract familyId from channel → family:{id}:status
            String[] parts = channel.split(":");
            String familyId = parts[1];

            plannerHelper.broadcastToFamily(familyId, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
