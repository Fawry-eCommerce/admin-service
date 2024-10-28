package com.fawry.admin_service.services.admin;

import com.fawry.admin_service.dtos.AdminDTO;
import com.fawry.admin_service.dtos.AdminResponse;
import com.fawry.admin_service.entities.Admin;

public interface AdminService {
    Admin saveAdmin(AdminDTO admin);
    AdminDTO addAdmin(AdminDTO adminDTO);
    AdminResponse findAdminById(Long id);
    AdminDTO updateAdminById(Long id, AdminDTO adminDTO);
    Admin findById(Long id);
    void deleteAdminById(Long id);
    Admin getAdminByEmail(String email);
    Admin getMyAdmin();
    boolean isEmailExists(String email);
}
