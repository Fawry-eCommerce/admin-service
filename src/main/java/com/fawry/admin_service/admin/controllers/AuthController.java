package com.fawry.admin_service.admin.controllers;

import com.fawry.admin_service.admin.dtos.AuthResponse;
import com.fawry.admin_service.admin.dtos.LoginRequest;
import com.fawry.admin_service.admin.dtos.RegisterRequest;
import com.fawry.admin_service.admin.services.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticate(loginRequest);
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/validate")
    public boolean validateToken(@RequestParam String token) {
        return authService.validateToken(token);
    }

    @GetMapping("/has-role")
    public boolean hasRole(
            @RequestParam String role,
            @RequestParam String token) {
        return authService.hasRole(token, role);
    }

}
