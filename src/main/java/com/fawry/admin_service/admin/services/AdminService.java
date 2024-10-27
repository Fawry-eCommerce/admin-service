package com.fawry.admin_service.admin.services;

import com.fawry.admin_service.admin.dtos.AdminDTO;
import com.fawry.admin_service.admin.entities.Admin;

public interface AdminService {
    AdminDTO addAdmin(AdminDTO adminDTO);
    AdminDTO findAdminById(Long id);
    AdminDTO updateAdminById(Long id, AdminDTO adminDTO);
    Admin findById(Long id);
    void deleteAdminById(Long id);
}
