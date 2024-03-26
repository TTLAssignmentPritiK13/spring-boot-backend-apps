package com.blogapp.service;


import com.blogapp.dto.RoleDto;
import com.blogapp.dto.UserDto;
import com.blogapp.entities.Role;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.repository.UserRepository;
import com.blogapp.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public RoleDto createRole(RoleDto roleDto){
        Role role = dtoToRole(roleDto);
        Role savedRole = userRoleRepository.save(role);
        return roleToDto(savedRole);
    }

    public RoleDto updateRole(RoleDto roleDto, Integer id){
        Role role = userRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", " roleName ", roleDto.getRoleName()));
        role.setRoleName(roleDto.getRoleName());
        role.setRoleDescription(roleDto.getRoleDescription());
        Role updatedRole = userRoleRepository.save(role);
        return roleToDto(updatedRole);
    }

    public RoleDto getRoleById(Integer id){
        Role role = userRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", " roleName with ", id));
        return roleToDto(role);
    }

    public List<RoleDto> getAllRoles(){
        List<Role> roles = userRoleRepository.findAll();
        List <RoleDto> roleDtos = roles.stream().map(role -> roleToDto(role)).collect(Collectors.toList());
        return roleDtos;
    }

    public void deleteRole(Integer id){
        Role role = userRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", " RoleId ", id));
        userRoleRepository.delete(role);
    }

    public Role dtoToRole(RoleDto roleDto){
        Role role = modelMapper.map(roleDto, Role.class);
        return role;
    }

    public RoleDto roleToDto(Role role){
        RoleDto roleDto = modelMapper.map(role, RoleDto.class);
        return roleDto;

    }
}
