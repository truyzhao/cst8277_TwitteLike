package com.truby.cst8277.service;

import com.truby.cst8277.dto.request.AddUserRequestDto;
import com.truby.cst8277.dto.request.LoginRequestDto;
import com.truby.cst8277.dto.request.UpdateUserRequestDto;
import com.truby.cst8277.dto.response.AddUserResponseDto;
import com.truby.cst8277.dto.response.UserDetailsResponseDto;
import com.truby.cst8277.dto.response.UserResponseDto;
import com.truby.cst8277.entities.Role;
import com.truby.cst8277.entities.User;
import com.truby.cst8277.exception.NotFoundUserId;
import com.truby.cst8277.exception.MessageProm;
import com.truby.cst8277.repository.UserRepository;
import com.truby.cst8277.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    @Autowired
    RoleRepository roleRepo;
    //find a user by id
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(NotFoundUserId::new);
    }

    //add a user to database
    public AddUserResponseDto add(AddUserRequestDto addUserRequestDto)  {
        User user = modelMapper.map(addUserRequestDto, User.class);
        if (userRepository.findByEmail(user.getEmail()) != null ||
                userRepository.findByEmail(user.getEmail()) != null){
        System.out.println("There is an account with this email address");
        }
        //set user's initial role when create a new user
        user.setRoles((Set<Role>) Arrays.asList(roleRepository.findByUsername("ROLE_USER")));
        userRepository.save(user);
        return modelMapper.map(user, AddUserResponseDto.class);
    }

    //delete a user by id
    public String deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(NotFoundUserId::new);
        userRepository.delete(user);
        return !userRepository.existsById(id) ? MessageProm.SUCCESSFULLY_DELETED : MessageProm.SOMETHING_WENT_WRONG;
    }

    //list all users
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream().map(item -> modelMapper.map(item, UserResponseDto.class)).toList();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    //update user's info
    public String updateUser(UpdateUserRequestDto updateUserRequestDto) {
        User user = userRepository.findById(updateUserRequestDto.getId()).orElseThrow(NotFoundUserId::new);
        user = modelMapper.map(updateUserRequestDto, User.class);
        userRepository.save(user);
        return user.getUserName() + " " + MessageProm.SUCCESSFULLY_UPDATED;
    }
    public UserDetailsResponseDto userDetails(UUID id) {
        User user = userRepository.findById(id).orElseThrow(NotFoundUserId::new);
        return modelMapper.map(user, UserDetailsResponseDto.class);
    }

    public void registerDefaultUser(User user) {
        Role roleUser = roleRepo.findByUsername("user");
        user.addRole(roleUser);
        userRepository.save(user);
    }

}
