package com.atman.aahara.WebSocket;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class PlannerWebSocketHandler implements WebSocketHandler {

    private final PlannerHelper plannerHelper;
    private final PlannerPubSubProducer plannerPubSubProducer;
    private final ValidateSession validateSession;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        SessionInfo info = validateSession.validateSession(session);
        if (info == null) return;
        plannerHelper.addSession(info.getFamilyId(), session);

        PlannerStatusEvent event = PlannerStatusEvent.builder()
                .data(UUID.randomUUID())
                .resourceType(ResourceType.USER)
                .plannerEventType(PlannerEventType.USER_ONLINE)
                .userID(info.getUserId())
                .build();

        plannerPubSubProducer.publishToFamily(info.getFamilyId(), event);

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        SessionInfo info = validateSession.validateSession(session);
        if (info == null) return;
        PlannerStatusEvent event = objectMapper.readValue(message.getPayload().toString(), PlannerStatusEvent.class);
        plannerPubSubProducer.publishToFamily(info.getFamilyId(), event);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        SessionInfo info = validateSession.validateSession(session);
        if (info == null) return;
        plannerHelper.removeSession(info.getFamilyId(), session);
        PlannerStatusEvent event = PlannerStatusEvent.builder()
                .data(UUID.randomUUID())
                .resourceType(ResourceType.USER)
                .plannerEventType(PlannerEventType.USER_OFFLINE)
                .userID(info.getUserId())
                .build();
        plannerPubSubProducer.publishToFamily(info.getFamilyId(), event);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }




}
