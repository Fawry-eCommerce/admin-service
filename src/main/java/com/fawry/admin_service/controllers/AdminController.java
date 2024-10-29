package com.fawry.admin_service.controllers;

import com.fawry.admin_service.dtos.AdminDTO;
import com.fawry.admin_service.dtos.AdminResponse;
import com.fawry.admin_service.entities.AdminRole;
import com.fawry.admin_service.services.admin.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("admins")
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public List<AdminResponse> findAllAdmins(){
        return adminService.findAllAdmins();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SUPER')")
    public AdminResponse addAdmin(@Valid @RequestBody AdminDTO adminDTO){
        return adminService.addAdmin(adminDTO);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('SUPER', 'NORMAL', 'ADVANCED')")
    public AdminResponse findAdminById(@PathVariable Long id){
        return adminService.findAdminById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('SUPER')")
    public AdminResponse updateAdminById(@PathVariable Long id,@Valid @RequestBody AdminDTO newAdminDTO){
        return adminService.updateAdminById(id, newAdminDTO);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('SUPER')")
    public void deleteAdminById(@PathVariable Long id){
        adminService.deleteAdminById(id);
    }

    @GetMapping("roles")
    public List<String> getAllRoles() {
        return Arrays.stream(AdminRole.values()).map(Enum::name).filter(role -> !role.equals("SUPER")).toList();
    }

}
