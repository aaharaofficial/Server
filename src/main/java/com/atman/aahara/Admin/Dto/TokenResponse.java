package com.atman.aahara.Admin.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TokenResponse {
    private String email;
    private String refreshToken;
    private String accessToken;
}
