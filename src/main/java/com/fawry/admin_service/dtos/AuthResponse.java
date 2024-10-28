package com.fawry.admin_service.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthResponse {
    private String token;
}
