package com.atman.aahara.Customer.Auth;

import com.atman.aahara.Customer.Auth.Dto.CustomerAuthRequest;
import com.atman.aahara.Customer.Auth.Dto.CustomerAuthResponse;
import com.atman.aahara.Customer.Auth.Dto.OTPResponse;
import com.atman.aahara.Customer.Auth.Dto.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerAuthApi {

    ResponseEntity<OTPResponse> sendOtpToMobileNumber(@RequestParam String mobileNumber);

    ResponseEntity<CustomerAuthResponse> verifyOtpFromMobileNumber(@RequestBody CustomerAuthRequest customerAuthRequest);

    ResponseEntity<TokenResponse> refreshToken(String token,String mobileNumber);

    ResponseEntity<?> logout(String token);
}
