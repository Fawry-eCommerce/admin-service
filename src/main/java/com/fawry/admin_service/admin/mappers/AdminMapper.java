package com.fawry.admin_service.admin.mappers;

import com.fawry.admin_service.admin.dtos.AdminDTO;
import com.fawry.admin_service.admin.entities.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Admin toAdmin(AdminDTO adminDTO);
    AdminDTO toAdminDTO(Admin admin);
}
