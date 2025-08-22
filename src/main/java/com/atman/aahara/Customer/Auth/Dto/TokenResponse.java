package com.atman.aahara.Customer.Auth.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}
