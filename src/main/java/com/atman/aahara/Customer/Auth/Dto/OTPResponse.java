package com.atman.aahara.Customer.Auth.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OTPResponse {
    private String identifier;
    private Instant validTo;
    private Integer currentAttempt;
}
