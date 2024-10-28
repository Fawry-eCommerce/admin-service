package com.fawry.admin_service.controllers;

import com.fawry.admin_service.dtos.AuthResponse;
import com.fawry.admin_service.dtos.LoginRequest;
import com.fawry.admin_service.dtos.RegisterRequest;
import com.fawry.admin_service.services.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public AuthResponse authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticate(loginRequest);
    }

    @PostMapping("register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("validate")
    public boolean validateToken(@RequestParam String token) {
        return authService.validateToken(token);
    }

    @GetMapping("has-role")
    public boolean hasRole(
            @RequestParam String role,
            @RequestParam String token) {
        return authService.hasRole(token, role);
    }

}
