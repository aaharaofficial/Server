package com.atman.aahara.Session.Domain;

import com.atman.aahara.Session.Port.CustomerSessionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerSessionDomainService {

    private final CustomerSessionPort customerSessionPort;

    public void invalidateAllSessions(String mobileNumber) {
        List<CustomerSession> sessions = customerSessionPort.findByMobileNumber(mobileNumber);
        for (CustomerSession session : sessions) {
            session.invalidate();
            customerSessionPort.save(session);
        }
    }
}
