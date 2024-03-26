package com.blogapp.service;

import com.blogapp.constant.AppConstant;
import com.blogapp.dto.UserDto;
import com.blogapp.entities.Role;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.repository.UserRepository;
import com.blogapp.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*public UserDto registerNewUser(UserDto userDto){
        User user = dtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = userRoleRepository.findById(AppConstant.USER_ROLE).get();
        user.getRoles().add(role);
        User newUser = userRepository.save(user);
        return userToDto(newUser);
    }*/

    public UserDto createUser(UserDto userDto){
        User user = dtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = userRoleRepository.findById(AppConstant.USER_ROLE).get();
        user.getRoles().add(role);
        User savedUser = userRepository.save(user);
        return userToDto(savedUser);
    }

    public UserDto updateUser(UserDto userDto, Integer id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAbout(userDto.getAbout());
        User updatedUser = userRepository.save(user);
        return userToDto(updatedUser);
    }

    public UserDto getUserById(Integer id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));
        return userToDto(user);
    }

    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        List <UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    public void deleteUser(Integer id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));
        userRepository.delete(user);
    }

    public User dtoToUser(UserDto userDto){
        // ModelMapper help to convert object of one type into another object. Here UserDto is converted into User entity
        User user = modelMapper.map(userDto, User.class);
        /*user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());*/
        return user;
    }

    public UserDto userToDto(User user){
        // User entity converted into UserDto object
        UserDto userDto = modelMapper.map(user, UserDto.class);
        /*userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());*/
        return userDto;

    }
}
