package com.fawry.admin_service.admin.mappers;

import com.fawry.admin_service.admin.dtos.RequestAdminDTO;
import com.fawry.admin_service.admin.entities.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestAdminMapper {
    Admin toAdmin(RequestAdminDTO requestAdminDTO);
    RequestAdminDTO toRequestAdminDTO(Admin admin);
}
