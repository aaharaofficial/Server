package com.atman.aahara.Admin.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Admin {
    private UUID id;
    private String email;
    private String password; // hashed
    private String name; // optional extra field
    private boolean active;

    // Domain logic: password verification
    public boolean verifyPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPassword, this.password);
    }

    // Domain logic: update password
    public void updatePassword(String newPassword, PasswordEncoder encoder) {
        this.password = encoder.encode(newPassword);
    }

    // Domain logic: activate/deactivate
    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }
}
