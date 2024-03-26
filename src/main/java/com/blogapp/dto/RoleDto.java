package com.blogapp.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto {

    private int roleId;

    @NotEmpty(message = "Role must not be empty or blank")
    @Size(min = 3, message = "Name must be min 3 characters")
    private String roleName;

    private String roleDescription;
}
