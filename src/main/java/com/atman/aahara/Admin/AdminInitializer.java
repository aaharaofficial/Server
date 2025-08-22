package com.atman.aahara.Admin;

import com.atman.aahara.Enum.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;
    private final AdminConfig adminConfig;


    @Override
    public void run(String... args) throws Exception {

        String adminEmail = adminConfig.getEmail();
        String adminPassword = adminConfig.getPassword();

        try {
            adminService.getAdminByEmail(adminEmail);
        } catch (Exception e) {
            Admin admin = Admin.builder()
                    .email(adminEmail)
                    .password(passwordEncoder.encode(adminPassword))
                    .role(Role.ADMIN)
                    .build();
            adminService.saveAdmin(admin);
            System.out.println("Default admin created : " + adminEmail);
        }
    }
}
