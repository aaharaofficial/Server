package com.atman.aahara.OneTimePass.infrastructure;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.time.Instant;

@RedisHash("OneTimePass")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OTPEntity {

    @Id
    private String identifier;

    private String otpCode;

    private Instant validTo;

    private Integer currentAttempt;

    @TimeToLive
    private Long ttl; // in seconds
}
