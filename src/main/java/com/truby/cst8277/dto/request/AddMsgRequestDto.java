package com.truby.cst8277.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class AddMsgRequestDto {
    private UUID userId;

    private String content;
}
