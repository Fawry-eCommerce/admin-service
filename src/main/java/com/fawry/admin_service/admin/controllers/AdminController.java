package com.fawry.admin_service.admin.controllers;

import com.fawry.admin_service.admin.dtos.AdminDTO;
import com.fawry.admin_service.admin.services.admin.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("admins")
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public AdminDTO addAdmin(@Valid @RequestBody AdminDTO adminDTO){
        return adminService.addAdmin(adminDTO);
    }

    @GetMapping("{id}")
    public AdminDTO findAdminById(@PathVariable Long id){
        return adminService.findAdminById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public AdminDTO updateAdminById(@PathVariable Long id,@Valid @RequestBody AdminDTO newAdminDTO){
        return adminService.updateAdminById(id, newAdminDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public void deleteAdminById(@PathVariable Long id){
        adminService.deleteAdminById(id);
    }

}
