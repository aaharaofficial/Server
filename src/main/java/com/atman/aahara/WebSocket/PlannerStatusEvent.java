package com.atman.aahara.WebSocket;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlannerStatusEvent<T> {
    private T data;
    private ResourceType resourceType;
    private PlannerEventType plannerEventType;
    private UUID userID;
}
