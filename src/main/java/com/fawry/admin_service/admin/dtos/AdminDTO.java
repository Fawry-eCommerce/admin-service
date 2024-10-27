package com.fawry.admin_service.admin.dtos;

import com.fawry.admin_service.admin.entities.AdminRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminDTO {
    private Long id;
    @NotNull(message = "First Name is Required")
    private String firstName;
    @NotNull(message = "Last Name is Required")
    private String lastName;
    @Email(message = "Email is Required")
    private String email;
    @NotNull(message = "Password is Required")
    private String password;
    @NotNull(message = "Active/Inactive is Required")
    private Boolean active;
    @NotNull(message = "Role is Required")
    private AdminRole role;
}
