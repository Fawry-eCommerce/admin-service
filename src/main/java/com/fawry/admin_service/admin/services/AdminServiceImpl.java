package com.fawry.admin_service.admin.services;

import com.fawry.admin_service.admin.dtos.AdminDTO;
import com.fawry.admin_service.admin.entities.Admin;
import com.fawry.admin_service.admin.exceptions.AdminNotFoundException;
import com.fawry.admin_service.admin.mappers.AdminMapper;
import com.fawry.admin_service.admin.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Override
    public AdminDTO addAdmin(AdminDTO adminDTO) {
        Admin admin = adminMapper.toAdmin(adminDTO);
        admin = adminRepository.save(admin);
        return adminMapper.toAdminDTO(admin);
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new AdminNotFoundException("Admin Not Found"));
    }

    @Override
    public AdminDTO findAdminById(Long id) {
        Admin admin = findById(id);
        return adminMapper.toAdminDTO(admin);
    }

    @Override
    public AdminDTO updateAdminById(Long id, AdminDTO adminDTO) {
        Admin admin = findById(id);
        Optional.ofNullable(adminDTO.getFirstName()).ifPresent(admin::setFirstName);
        Optional.ofNullable(adminDTO.getLastName()).ifPresent(admin::setLastName);
        Optional.ofNullable(adminDTO.getEmail()).ifPresent(admin::setEmail);
        Optional.ofNullable(adminDTO.getPassword()).ifPresent(admin::setPassword);
        Optional.ofNullable(adminDTO.getActive()).ifPresent(admin::setActive);
        Optional.ofNullable(adminDTO.getRole()).ifPresent(admin::setRole);
        return adminMapper.toAdminDTO(adminRepository.save(admin));
    }

    @Override
    public void deleteAdminById(Long id) {
        adminRepository.deleteById(id);
    }
}
