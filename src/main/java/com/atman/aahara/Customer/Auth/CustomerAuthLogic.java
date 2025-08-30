package com.atman.aahara.Customer.Auth;

import com.atman.aahara.Customer.Auth.Dto.CustomerAuthRequest;
import com.atman.aahara.Customer.Auth.Dto.CustomerAuthResponse;
import com.atman.aahara.Customer.Auth.Dto.OTPResponse;
import com.atman.aahara.Customer.Auth.Dto.TokenResponse;
import com.atman.aahara.Customer.Base.Customer;
import com.atman.aahara.Customer.Base.CustomerService;
import com.atman.aahara.Notification.SmsNotificationService;
import com.atman.aahara.OneTimePass.OTPService;
import com.atman.aahara.OneTimePass.OTPStorageService;
import com.atman.aahara.OneTimePass.OneTimePass;
import com.atman.aahara.Security.JwtService;
import com.atman.aahara.Session.CustomerSession;
import com.atman.aahara.Session.CustomerSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerAuthLogic implements CustomerAuthService {

    private final OTPStorageService otpStorageService;
    private final OTPService otpService;
    private final SmsNotificationService smsNotificationService;
    private final CustomerService customerService;
    private final CustomerSessionService customerSessionService;
    private final JwtService jwtService;

    @Override
    public OTPResponse sendOtpToMobileNumber(String mobileNumber) {
        String otpCode = otpService.generateOTP();
        OneTimePass session = otpStorageService.saveSession(mobileNumber, otpCode);

        // Send SMS asynchronously
        smsNotificationService.sendSmsNotification(mobileNumber, "Your OTP is: " + otpCode);

        return OTPResponse.builder()
                .currentAttempt(session.getCurrentAttempt())
                .identifier(session.getIdentifier())
                .validTo(session.getValidTo())
                .build();
    }

    @Override
    public TokenResponse refreshToken(String token, String mobileNumber) {
        jwtService.isTokenValid(token, mobileNumber);
        Customer customer = customerService.getCustomerByMobileNumber(mobileNumber);
        CustomerSession sessionByRefreshToken = customerSessionService.getSessionByRefreshToken(token);
        if (!sessionByRefreshToken.isValid()) {
            throw new CredentialsExpiredException("token was invalid , Login in again  ");
        }
        String accessToken = jwtService.generateToken(customer.getMobileNumber(), "CUSTOMER");
        String refreshToken = jwtService.generateRefreshToken(customer.getMobileNumber());
        sessionByRefreshToken.setRefreshToken(refreshToken);
        sessionByRefreshToken.setValid(true);
        sessionByRefreshToken.setExpiresAt(jwtService.getRefreshExpiryDuration());
        sessionByRefreshToken.setLastRefreshedAt(LocalDateTime.now());
        customerSessionService.saveSession(sessionByRefreshToken);
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public CustomerAuthResponse verifyOtpUsingMobileNumber(CustomerAuthRequest customerAuthRequest) {
        OneTimePass otpSession = otpStorageService.getSession(customerAuthRequest.getMobileNumber());
        log.info("otp session" + otpSession.getIdentifier() +  otpSession.getOtpCode() + customerAuthRequest.getOtpCode());
        boolean verificationStatus = otpService.verify(otpSession.getOtpCode(), customerAuthRequest.getOtpCode());
        if (!verificationStatus) {
            throw new CredentialsExpiredException(" your otp is not matched .." + customerAuthRequest.getMobileNumber());
        }
        boolean isCustomer = customerService.doesCustomerExists(customerAuthRequest.getMobileNumber());
        String accessToken = jwtService.generateToken(customerAuthRequest.getMobileNumber(), "CUSTOMER");
        String refreshToken = jwtService.generateRefreshToken(customerAuthRequest.getMobileNumber());
        CustomerSession customerSession = CustomerSession.builder().mobileNumber(customerAuthRequest.getMobileNumber())
                .ipAddress(customerAuthRequest.getIpAddress()).userAgent(customerAuthRequest.getUserAgent())
                .longitude(customerAuthRequest.getLongitude()).latitude(customerAuthRequest.getLatitude())
                .issuedAt(LocalDateTime.now()).expiresAt(jwtService.getRefreshExpiryDuration()).build();
        CustomerSession authSession = customerSessionService.saveSession(customerSession);
        return CustomerAuthResponse.builder()
                .isCustomer(isCustomer)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


    @Override
    public void logout(String refreshToken) {
        customerSessionService.invalidateToken(refreshToken);
    }

}
