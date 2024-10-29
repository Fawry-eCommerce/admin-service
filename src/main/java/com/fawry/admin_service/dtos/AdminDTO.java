package com.fawry.admin_service.dtos;

import com.fawry.admin_service.entities.AdminRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AdminDTO {
    private Long id;
    @NotNull(message = "First Name is Required")
    private String firstName;
    @NotNull(message = "Last Name is Required")
    private String lastName;
    @Email(message = "Email Format is Required")
    @NotNull(message = "Email is Required")
    private String email;
    @NotNull(message = "Password is Required")
    private String password;
    private Boolean active;
    private AdminRole role;
}
