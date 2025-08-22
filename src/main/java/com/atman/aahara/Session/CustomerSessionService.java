package com.atman.aahara.Session;

import java.util.List;

public interface CustomerSessionService {

    CustomerSession saveSession(CustomerSession customerSession);

    void invalidateToken(String refreshToken);

    CustomerSession getSessionByRefreshToken(String refreshToken);

    List<CustomerSession> getSessionByMobileNumber(String mobileNumber);

}
