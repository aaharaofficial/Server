package com.atman.aahara.WebSocket;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PlannerHelper {

    private final Map<String, List<WebSocketSession>> familySessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    public void addSession(String familyId, WebSocketSession session) {
        familySessions.computeIfAbsent(familyId, k -> new ArrayList<>()).add(session);
    }

    public void removeSession(String familyId,WebSocketSession session) {
        familySessions.getOrDefault(familyId, Collections.emptyList()).remove(session);
    }

    public List<WebSocketSession> getFamilySessions(String familyId) {
        return familySessions.getOrDefault(familyId, Collections.emptyList());
    }

    public void broadcastToFamily(String familyId, PlannerStatusEvent event) {
        List<WebSocketSession> sessions = getFamilySessions(familyId);
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try {
                    String message = objectMapper.writeValueAsString(event);
                    session.sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
