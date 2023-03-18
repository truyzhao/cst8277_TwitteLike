package com.truby.cst8277.controller;

import com.truby.cst8277.dto.request.AddUserRequestDto;
import com.truby.cst8277.dto.request.UpdateUserRequestDto;
import com.truby.cst8277.dto.response.AddUserResponseDto;
import com.truby.cst8277.dto.response.UserDetailsResponseDto;
import com.truby.cst8277.dto.response.UserResponseDto;
import com.truby.cst8277.entities.Role;
import com.truby.cst8277.entities.User;
import com.truby.cst8277.repository.RoleRepository;
import com.truby.cst8277.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UsersController {
    private UserService userService;
    private RoleRepository roleRepository;

    @PostMapping("add")
    public ResponseEntity<AddUserResponseDto> add(@RequestBody AddUserRequestDto addUserRequestDto) {
        return new ResponseEntity<>(userService.add(addUserRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> delete(@RequestParam(name = "id") UUID id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<UserResponseDto>> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("list")
    public String listUsers(Model model) {
        List<User> listUsers = null;
        //listUsers=userService.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @PutMapping("update")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserRequestDto updateUserRequestDto){
        return new ResponseEntity<>(userService.updateUser(updateUserRequestDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("details")
    public ResponseEntity<UserDetailsResponseDto> userDetails(@RequestParam(name = "id") UUID id){
        return new ResponseEntity<>(userService.userDetails(id) , HttpStatus.OK);
    }


    @GetMapping("/role/{userName}")

    public Role.RoleType getRole(@PathVariable String userName) {
      return roleRepository.findByUsername(userName).getRoleName();
    }
}
