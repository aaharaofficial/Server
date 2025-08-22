package com.atman.aahara.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController implements AdminApi{

    private final AdminService adminService;

    @Override
    @PostMapping("/refresh")
    public ResponseEntity<AdminResponse> refreshToken(@RequestParam String token) {
        AdminResponse adminResponse = adminService.refreshToken(token);
        return ResponseEntity.ok(adminResponse);
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<AdminResponse> login(@RequestParam  String email, @RequestParam String password) {
        AdminResponse loginUserResponse = adminService.login(email, password);
        return ResponseEntity.ok(loginUserResponse);
    }
}
