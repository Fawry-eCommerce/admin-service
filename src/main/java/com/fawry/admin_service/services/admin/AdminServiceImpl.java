package com.fawry.admin_service.services.admin;

import com.fawry.admin_service.dtos.AdminDTO;
import com.fawry.admin_service.dtos.AdminResponse;
import com.fawry.admin_service.entities.Admin;
import com.fawry.admin_service.entities.AdminRole;
import com.fawry.admin_service.exceptions.AdminNotFoundException;
import com.fawry.admin_service.mappers.AdminMapper;
import com.fawry.admin_service.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Override
    public List<AdminResponse> findAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(adminMapper::toAdminResponse)
                .toList();
    }

    @Override
    public Admin saveAdmin(AdminDTO admin) {
        if (isEmailExists(admin.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        Admin newAdmin = adminMapper.toAdmin(admin);
        newAdmin.setActive(true);
        newAdmin.setRole(admin.getRole() != null ? admin.getRole() : AdminRole.NORMAL);
        return adminRepository.save(newAdmin);
    }

    @Override
    public AdminResponse addAdmin(AdminDTO adminDTO) {
        return adminMapper.toAdminResponse(saveAdmin(adminDTO));
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElseThrow(
                () -> new AdminNotFoundException("Admin Not Found")
        );
    }

    @Override
    public AdminResponse findAdminById(Long id) {
        return adminMapper.toAdminResponse(findById(id));
    }

    @Override
    public AdminResponse updateAdminById(Long id, AdminDTO adminDTO) {
        Admin admin = findById(id);
        updateAdminDetails(admin, adminDTO);
        return adminMapper.toAdminResponse(adminRepository.save(admin));
    }

    @Override
    public void deleteAdminById(Long id) {
        Admin admin = findById(id);
        adminRepository.delete(admin);
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

    private void updateAdminDetails(Admin admin, AdminDTO adminDTO) {
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());
        admin.setActive(adminDTO.getActive());
        admin.setRole(adminDTO.getRole() != null? adminDTO.getRole() : AdminRole.NORMAL);
    }
}
