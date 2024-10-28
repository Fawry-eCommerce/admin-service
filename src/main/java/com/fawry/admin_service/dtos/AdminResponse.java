package com.fawry.admin_service.dtos;

import com.fawry.admin_service.entities.AdminRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AdminResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean active;
    private AdminRole role;
}
