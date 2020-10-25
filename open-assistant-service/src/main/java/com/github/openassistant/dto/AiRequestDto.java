package com.github.openassistant.dto;

import lombok.Data;

@Data
public class AiRequestDto {

    private String question;

    public AiRequestDto(String question) {
        this.question = question;
    }
}
