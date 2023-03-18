package com.truby.cst8277.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddUserRequestDto {
    private String userName;
    private String email;
    private String password;
    //private boolean hasProRole;
    //private boolean hasSubRole;

}
