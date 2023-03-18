package com.truby.cst8277.repository;

import com.truby.cst8277.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    public Role findByUsername(String userName);
}
