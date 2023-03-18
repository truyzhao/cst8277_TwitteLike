package com.truby.cst8277.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FOLLOWERS")
public class Follower {
    @Id
    @Type(type = "uuid-char")
    private UUID id;
    private String userName;
    @JsonManagedReference
    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Set<User> users = new HashSet<>();
}
