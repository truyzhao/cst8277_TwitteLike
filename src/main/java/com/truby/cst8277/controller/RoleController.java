package com.truby.cst8277.controller;

import com.truby.cst8277.entities.Role;
import com.truby.cst8277.repository.RoleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class RoleController {

    private RoleRepository roleRepository;

    @GetMapping("/role/details/{id}")

    public Role getRole(@PathVariable Integer id) {
        if(roleRepository.findById(id).isPresent())
            return roleRepository.findById(id).get();
        else return null;
    }

    @GetMapping("/role/all")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }


}
