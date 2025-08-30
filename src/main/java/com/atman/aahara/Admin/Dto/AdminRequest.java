package com.atman.aahara.Admin.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AdminRequest {
    @NotNull(message = "email should not be null")
    private String email;
    @NotNull(message = "password should not be null")
    private String password;
}
