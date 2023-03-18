package com.truby.cst8277.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MsgDetailsResponseDto {
    private String producerName;
    private String content;
    private LocalDateTime msgCreateDate;

}
