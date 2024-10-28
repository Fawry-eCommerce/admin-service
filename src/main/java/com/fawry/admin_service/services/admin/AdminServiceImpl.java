package com.fawry.admin_service.services.admin;

import com.fawry.admin_service.dtos.AdminDTO;
import com.fawry.admin_service.entities.Admin;
import com.fawry.admin_service.exceptions.AdminNotFoundException;
import com.fawry.admin_service.mappers.AdminMapper;
import com.fawry.admin_service.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Override
    public Admin saveAdmin(AdminDTO admin) {
        return adminRepository.save(adminMapper.toAdmin(admin));
    }

    @Override
    public AdminDTO addAdmin(AdminDTO adminDTO) {
        return adminMapper.toAdminDTO(saveAdmin(adminDTO));
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

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email).orElseThrow(
                () -> new AdminNotFoundException("Admin Not Found")
        );
    }

    @Override
    public Admin getMyAdmin() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return adminRepository.findByEmail(email).orElseThrow(
                () -> new AdminNotFoundException("Admin Not Found")
        );
    }

    @Override
    public boolean isEmailExists(String email) {
        return adminRepository.existsByEmail(email);
    }
}
