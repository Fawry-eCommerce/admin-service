package com.fawry.admin_service.admin.mappers;

import com.fawry.admin_service.admin.dtos.ResponseAdminDTO;
import com.fawry.admin_service.admin.entities.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponseAdminMapper {
    Admin toAdmin(ResponseAdminDTO responseAdminDTO);
    ResponseAdminDTO toResponseAdminDTO(Admin admin);
}
