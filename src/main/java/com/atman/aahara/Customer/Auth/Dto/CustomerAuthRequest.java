package com.atman.aahara.Customer.Auth.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomerAuthRequest {
    private String ipAddress;
    private String userAgent;
    private String latitude;
    private String longitude;
    private String mobileNumber;
    private String otpCode;
}
