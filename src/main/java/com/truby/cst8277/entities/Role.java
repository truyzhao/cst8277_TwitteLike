package com.truby.cst8277.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_ROLE")
public class Role {
    @Id
    @Column(name = "role_id", unique = true)
    private int id;

    @Column(name = "roleName", unique = true)
    private String roleName;
    private String description;

    @ManyToMany(targetEntity = User.class, mappedBy = "roles", cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<User> users;
}
