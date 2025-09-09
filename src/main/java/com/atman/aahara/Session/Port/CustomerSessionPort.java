package com.atman.aahara.Session.Port;


import com.atman.aahara.Session.Domain.CustomerSession;

import java.util.List;
import java.util.Optional;

public interface CustomerSessionPort {

    CustomerSession save(CustomerSession session);

    Optional<CustomerSession> findByRefreshToken(String refreshToken);

    List<CustomerSession> findByMobileNumber(String mobileNumber);

    List<CustomerSession> findAll();
}
