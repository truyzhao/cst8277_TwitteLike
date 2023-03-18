package com.truby.cst8277.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddUserResponseDto {
    private String userName;
    private String email;
    private String password;
    //private boolean hasProRole;
    //private boolean hasSubRole;
    //private LocalDate userCreateDate;
}
