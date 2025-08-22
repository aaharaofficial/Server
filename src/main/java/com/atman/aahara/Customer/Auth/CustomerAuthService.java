package com.atman.aahara.Customer.Auth;

import com.atman.aahara.Customer.Auth.Dto.CustomerAuthRequest;
import com.atman.aahara.Customer.Auth.Dto.CustomerAuthResponse;
import com.atman.aahara.Customer.Auth.Dto.OTPResponse;
import com.atman.aahara.Customer.Auth.Dto.TokenResponse;

public interface CustomerAuthService {
    OTPResponse sendOtpToMobileNumber(String mobileNumber);
    CustomerAuthResponse verifyOtpUsingMobileNumber(CustomerAuthRequest customerAuthRequest);
    TokenResponse refreshToken(String refreshToken, String mobileNumber);
    void logout(String refreshToken);
}
