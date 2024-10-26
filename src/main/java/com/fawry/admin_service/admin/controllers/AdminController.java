package com.fawry.admin_service.admin.controllers;

import com.fawry.admin_service.admin.dtos.RequestAdminDTO;
import com.fawry.admin_service.admin.dtos.ResponseAdminDTO;
import com.fawry.admin_service.admin.services.AdminService;
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
    public ResponseEntity<ResponseAdminDTO> addAdmin(@RequestBody RequestAdminDTO requestAdminDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.addAdmin(requestAdminDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseAdminDTO> findAdminById(@PathVariable Long id){
        ResponseAdminDTO responseAdminDTO = adminService.findAdminById(id);
        if(responseAdminDTO != null){
            return ResponseEntity.ok(responseAdminDTO);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseAdminDTO> updateAdminById(@PathVariable Long id, @RequestBody RequestAdminDTO requestAdminDTO){
        ResponseAdminDTO responseAdminDTO = adminService.updateAdminById(id, requestAdminDTO);
        if(responseAdminDTO != null){
            return ResponseEntity.ok(responseAdminDTO);
        }
        return ResponseEntity.badRequest().body(null);

    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdminById(@PathVariable Long id){
        adminService.deleteAdminById(id);
    }
}
