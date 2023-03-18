package com.truby.cst8277.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class UpdateUserRequestDto {

    private UUID id;
    private String userName;
    private String email;
    private String password;
    private boolean hasProRole;
    private boolean hasSubRole;
    private LocalDate userCreateDate;
}
