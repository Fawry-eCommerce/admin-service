package com.fawry.admin_service.admin.services;

import com.fawry.admin_service.admin.dtos.RequestAdminDTO;
import com.fawry.admin_service.admin.dtos.ResponseAdminDTO;
import com.fawry.admin_service.admin.entities.Admin;

public interface AdminService {
    ResponseAdminDTO addAdmin(RequestAdminDTO requestAdminDTO);
    ResponseAdminDTO findAdminById(Long id);
    ResponseAdminDTO updateAdminById(Long id, RequestAdminDTO requestAdminDTO);
    Admin findById(Long id);
    void deleteAdminById(Long id);
}
