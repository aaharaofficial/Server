package com.atman.aahara.WebSocket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ValidateSession {

    public SessionInfo validateSession(WebSocketSession session) throws IOException {
        String familyId = getFamilyIdFromSession(session);
        UUID userId = getUserIdFromSession(session);

        if (familyId == null || userId == null) {
            session.close(CloseStatus.BAD_DATA.withReason("Missing familyId or userId"));
            return null;
        }

        return new SessionInfo(familyId, userId);
    }


    private String getFamilyIdFromSession(WebSocketSession session) {
        if (session.getUri() == null) return null;
        String query = session.getUri().getQuery();
        if (query == null) return null;
        return Arrays.stream(query.split("&"))
                .filter(s -> s.startsWith("familyId="))
                .map(s -> s.split("=")[1])
                .findFirst().orElse(null);
    }

    private UUID getUserIdFromSession(WebSocketSession session) {
        return UUID.randomUUID();
    }

}
