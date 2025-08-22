package com.atman.aahara.OneTimePass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;


@RedisHash("OneTimePass")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OneTimePass {
    @Id
    private String identifier;
    private String otpCode;
    private Instant validTo;
    private Integer currentAttempt;
    @TimeToLive
    private Long ttl;
}
