package com.truby.cst8277.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Remp_USERS")
@Component
public class GithubClient {

    @Id
    @Column(name = "github_id")
    private String githubId;
    @Column(name = "github_Name", unique = true)
    private String githubName;

    @Column(name = "email")
    private String email;




}
