package com.atman.aahara.WebSocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@AllArgsConstructor
@Data
@Builder
public class SessionInfo {
    private final String familyId;
    private final   UUID userId;
}
