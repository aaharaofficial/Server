package com.atman.aahara.Session.Application;


import com.atman.aahara.Session.Port.CustomerSessionPort;
import com.atman.aahara.Session.Domain.CustomerSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerSessionApplication {

    private final CustomerSessionPort sessionPort;

    public CustomerSession saveSession(CustomerSession session) {
        return sessionPort.save(session);
    }

    public void invalidateToken(String refreshToken) {
        CustomerSession session = sessionPort.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Session not found"));
        session.invalidate();
        sessionPort.save(session);
    }

    public CustomerSession getSessionByRefreshToken(String refreshToken) {
        return sessionPort.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Session not found"));
    }

    public List<CustomerSession> getSessionsByMobileNumber(String mobileNumber) {
        return sessionPort.findByMobileNumber(mobileNumber);
    }

    public List<CustomerSession> getAllSessions() {
        return sessionPort.findAll();
    }
}
