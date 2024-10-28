package com.fawry.admin_service.mappers;

import com.fawry.admin_service.dtos.AdminDTO;
import com.fawry.admin_service.dtos.AdminResponse;
import com.fawry.admin_service.entities.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Admin toAdmin(AdminDTO adminDTO);
    AdminDTO toAdminDTO(Admin admin);
    AdminResponse toAdminResponse(Admin admin);
}
