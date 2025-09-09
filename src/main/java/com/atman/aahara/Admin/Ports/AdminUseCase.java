package com.atman.aahara.Admin.Ports;

import com.atman.aahara.Admin.Domain.Admin;

import java.util.UUID;

public interface AdminUseCase {
    Admin getAdmin(UUID id);
    Admin getAdminByEmail(String email);
}
