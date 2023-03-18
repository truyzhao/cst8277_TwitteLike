package com.truby.cst8277.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDetailsResponseDto {
    private String userName;
    private String email;
    private String password;
    private LocalDate userCreateDate;

}
