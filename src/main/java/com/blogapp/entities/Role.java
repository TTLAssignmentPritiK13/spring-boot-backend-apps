package com.blogapp.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
public class Role {

    @Id
    private int roleId;
    private String roleName;
    private String roleDescription;
}
