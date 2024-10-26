package com.fawry.admin_service.admin.dtos;

import com.fawry.admin_service.admin.entities.AdminRole;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseAdminDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean active;
    private AdminRole role;
}
