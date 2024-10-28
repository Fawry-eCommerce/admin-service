package com.fawry.admin_service.services.auth;

import com.fawry.admin_service.dtos.AuthResponse;
import com.fawry.admin_service.dtos.LoginRequest;
import com.fawry.admin_service.dtos.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest);
    AuthResponse authenticate(LoginRequest loginRequest);
    boolean validateToken(String token);
    boolean hasRole(String token, String role);
}
