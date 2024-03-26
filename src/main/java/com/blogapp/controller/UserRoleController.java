package com.blogapp.controller;


import com.blogapp.dto.ApiResponse;
import com.blogapp.dto.RoleDto;
import com.blogapp.dto.UserDto;
import com.blogapp.service.UserRoleService;
import com.blogapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/create")
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto roleDto){
        RoleDto createdRole = userRoleService.createRole(roleDto);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoleDto> updateRole(@Valid @RequestBody RoleDto roleDto, @PathVariable Integer id ){
        RoleDto updatedRole = userRoleService.updateRole(roleDto,id);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable int id){
        RoleDto roleDto = userRoleService.getRoleById(id);
        return new ResponseEntity<>(roleDto,HttpStatus.FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        List<RoleDto> roleDtos = userRoleService.getAllRoles();
        return new ResponseEntity<>(roleDtos,HttpStatus.FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse> deleteRole(@PathVariable int id){
        userRoleService.deleteRole(id);
        return new ResponseEntity<>(new ApiResponse("User Role Deleted Successfully !!", true), HttpStatus.OK);
    }
}
