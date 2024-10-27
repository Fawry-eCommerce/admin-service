package com.fawry.admin_service.admin.controllers;

import com.fawry.admin_service.admin.dtos.AdminDTO;
import com.fawry.admin_service.admin.services.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("admins")
public class AdminController {
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminDTO> addAdmin(@Valid @RequestBody AdminDTO adminDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.addAdmin(adminDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<AdminDTO> findAdminById(@PathVariable Long id){
        AdminDTO adminDTO = adminService.findAdminById(id);
        return ResponseEntity.ok(adminDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<AdminDTO> updateAdminById(@PathVariable Long id,@Valid @RequestBody AdminDTO newAdminDTO){
        AdminDTO adminDTO = adminService.updateAdminById(id, newAdminDTO);
        return ResponseEntity.ok(adminDTO);

    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdminById(@PathVariable Long id){
        adminService.deleteAdminById(id);
    }
}
