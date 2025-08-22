package com.atman.aahara.Session;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerSessionLogic implements CustomerSessionService {

    private final CustomerSessionRepository sessionRepository;

    @Override
    public CustomerSession saveSession(CustomerSession customerSession) {
        return sessionRepository.save(customerSession);
    }

    @Override
    public void invalidateToken(String refreshToken) {
        CustomerSession session = getSessionByRefreshToken(refreshToken);
        session.setValid(false);
        session.setLoggedOutAt(LocalDateTime.now());
        saveSession(session);
    }


    @Override
    public CustomerSession getSessionByRefreshToken(String refreshToken) {
        return sessionRepository.findByRefreshToken(refreshToken).orElse(null);
    }

    @Override
    public List<CustomerSession> getSessionByMobileNumber(String mobileNumber) {
        return sessionRepository.findByMobileNumber(mobileNumber);
    }
}
