package com.atman.aahara.Security;

import com.atman.aahara.Admin.Ports.AdminUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("ADMIN")
@RequiredArgsConstructor
public class AdminDetailsProvider implements UserDetailsProvider {

    private final AdminUseCase adminUseCase;

    @Override
    public Object loadUser(String subject) {
        return adminUseCase.getAdminByEmail(subject);
    }
}
