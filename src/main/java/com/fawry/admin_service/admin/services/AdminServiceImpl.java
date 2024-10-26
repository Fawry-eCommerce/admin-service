package com.fawry.admin_service.admin.services;

import com.fawry.admin_service.admin.dtos.RequestAdminDTO;
import com.fawry.admin_service.admin.dtos.ResponseAdminDTO;
import com.fawry.admin_service.admin.entities.Admin;
import com.fawry.admin_service.admin.mappers.RequestAdminMapper;
import com.fawry.admin_service.admin.mappers.ResponseAdminMapper;
import com.fawry.admin_service.admin.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    private final RequestAdminMapper requestAdminMapper;
    private final ResponseAdminMapper responseAdminMapper;

    @Override
    public ResponseAdminDTO addAdmin(RequestAdminDTO requestAdminDTO) {
        Admin admin = requestAdminMapper.toAdmin(requestAdminDTO);
        admin = adminRepository.save(admin);
        return responseAdminMapper.toResponseAdminDTO(admin);
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseAdminDTO findAdminById(Long id) {
        Admin admin = findById(id);
        if (admin == null) {
            return null;
        }
        return responseAdminMapper.toResponseAdminDTO(admin);
    }

    @Override
    public ResponseAdminDTO updateAdminById(Long id, RequestAdminDTO requestAdminDTO) {
        Admin admin = findById(id);
        if (admin == null){
            return null;
        }
        if (requestAdminDTO.getFirstName() != null){
            admin.setFirstName(requestAdminDTO.getFirstName());
        }
        if (requestAdminDTO.getLastName() != null){
            admin.setLastName(requestAdminDTO.getLastName());
        }
        if (requestAdminDTO.getEmail() != null){
            admin.setEmail(requestAdminDTO.getEmail());
        }
        if (requestAdminDTO.getPassword() != null){
            admin.setPassword(requestAdminDTO.getPassword());
        }
        if (requestAdminDTO.getActive() != null){
            admin.setActive(requestAdminDTO.getActive());
        }
        if (requestAdminDTO.getRole() != null){
            admin.setRole(requestAdminDTO.getRole());
        }
        return responseAdminMapper.toResponseAdminDTO(adminRepository.save(admin));
    }

    @Override
    public void deleteAdminById(Long id) {
        adminRepository.deleteById(id);
    }
}
