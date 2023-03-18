package com.truby.cst8277.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateMsgRequestDto {
    private UUID messageId;
    private String content;
}
