package com.fawry.admin_service.admin.services.auth;

import com.fawry.admin_service.admin.entities.Admin;
import com.fawry.admin_service.admin.entities.AdminRole;
import com.fawry.admin_service.admin.services.jwt.JwtService;
import com.fawry.admin_service.admin.dtos.*;
import com.fawry.admin_service.admin.services.admin.AdminService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        AdminDTO admin = adminService.addAdmin(
                AdminDTO.builder()
                        .firstName(registerRequest.getFirstName())
                        .lastName(registerRequest.getLastName())
                        .email(registerRequest.getEmail())
                        .password(passwordEncoder.encode(registerRequest.getPassword()))
                        .active(true)
                        .role(
                                registerRequest.getRole() != null
                                        ? registerRequest.getRole()
                                        : AdminRole.NORMAL)
                        .build()
        );
        String token = jwtService.generateToken(admin.getEmail());
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponse authenticate(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail() , loginRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("Username or password is incorrect");
        }

        String token = jwtService.generateToken(loginRequest.getEmail());
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public boolean validateToken(String token) {
        String email = jwtService.extractClaim(token, Claims::getSubject);
        Admin admin = adminService.getAdminByEmail(email);
        return jwtService.isTokenValid(token, admin);
    }

    @Override
    public boolean hasRole(String token, String role) {
        return jwtService.hasRole(token , role);
    }
}
