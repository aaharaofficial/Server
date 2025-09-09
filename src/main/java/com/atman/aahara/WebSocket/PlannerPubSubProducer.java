package com.atman.aahara.WebSocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PlannerPubSubProducer {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void publishToFamily(String familyId, PlannerStatusEvent event) throws JsonProcessingException {
        String channel = "family:" + familyId + ":status";
        String message = objectMapper.writeValueAsString(event);
        redisTemplate.convertAndSend(channel, message);
    }
}
