package com.fawry.admin_service.util;

import com.fawry.admin_service.dtos.AdminDTO;
import com.fawry.admin_service.entities.AdminRole;
import com.fawry.admin_service.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandLineStartupRunner implements CommandLineRunner {

    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        addSuperAdmin();
    }

    void addSuperAdmin() {
        boolean isSuperAdminExists = adminService.isEmailExists("yousofkortam@gmail.com");
        if (isSuperAdminExists) {
            return;
        }
        this.adminService.saveAdmin(
                AdminDTO.builder()
                        .firstName("Yousof")
                        .lastName("Kortam")
                        .email("yousofkortam@gmail.com")
                        .active(true)
                        .role(AdminRole.SUPER)
                        .password(passwordEncoder.encode("123456"))
                        .build()
        );
    }
}
