package com.fawry.admin_service.admin.services.auth;

import com.fawry.admin_service.admin.dtos.AuthResponse;
import com.fawry.admin_service.admin.dtos.LoginRequest;
import com.fawry.admin_service.admin.dtos.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest);
    AuthResponse authenticate(LoginRequest loginRequest);
    boolean validateToken(String token);
    boolean hasRole(String token, String role);
}
