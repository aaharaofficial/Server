package com.atman.aahara.Customer.Auth;

import com.atman.aahara.Customer.Auth.Dto.CustomerAuthRequest;
import com.atman.aahara.Customer.Auth.Dto.CustomerAuthResponse;
import com.atman.aahara.Customer.Auth.Dto.OTPResponse;
import com.atman.aahara.Customer.Auth.Dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer/auth")
@Slf4j
@Tag(name = "Customer Authentication", description = "APIs for sending and verifying OTP")
public class CustomerAuthController implements CustomerAuthApi {

    private final CustomerAuthService customerAuthService;

    @PostMapping("/send-otp")
    @Operation(summary = "Send OTP", description = "Send OTP to customer mobile number")
    @Override
    public ResponseEntity<OTPResponse> sendOtpToMobileNumber(@RequestParam String mobileNumber) {
        OTPResponse otpResponse = customerAuthService.sendOtpToMobileNumber(mobileNumber);
        return ResponseEntity.ok(otpResponse);
    }

    @Override
    public ResponseEntity<TokenResponse> refreshToken(String token, String mobileNumber) {
        TokenResponse tokenResponse = customerAuthService.refreshToken(token, mobileNumber);
        return ResponseEntity.ok(tokenResponse);
    }

    @Override
    public ResponseEntity<?> logout(String token) {
        customerAuthService.logout(token);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/verify-otp")
    @Operation(summary = "Verify OTP", description = "Verify OTP using mobile number")
    @Override
    public ResponseEntity<CustomerAuthResponse> verifyOtpFromMobileNumber(CustomerAuthRequest customerAuthRequest) {
        CustomerAuthResponse customerAuthResponse = customerAuthService.verifyOtpUsingMobileNumber(customerAuthRequest);
        return ResponseEntity.ok(customerAuthResponse);
    }
}
