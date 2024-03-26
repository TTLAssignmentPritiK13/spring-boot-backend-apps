package com.blogapp.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty(message = "Name must not be empty or blank")
    @Size(min = 4, message = "Name must be min 4 characters")
    private String name;

    @Email(message = "Email address is not valid")
    private String email;

    @NotEmpty(message = "Password must not be null")
    @Size(min = 8, max = 15, message = "Password must be min of 8 and max of 15 characters")
    private String password;

    @NotEmpty(message = "Tell about yourself")
    @Size(min = 5, max = 50)
    private String about;
}
