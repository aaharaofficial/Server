package com.atman.aahara.Admin;

import com.atman.aahara.Enum.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;
    private final AdminConfig adminConfig;


    @Override
    public void run(String... args) throws Exception {

        String adminEmail = adminConfig.getEmail();
        String adminPassword = adminConfig.getPassword();
            Admin admin = Admin.builder()
                    .email(adminEmail)
                    .password(adminPassword)
                    .role(Role.ADMIN)
                    .build();
            adminService.saveAdmin(admin);
            System.out.println("Default admin created : " + adminEmail);
    }
}
