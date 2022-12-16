package com.hanghae.gamemini.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDto {
    private String statusMsg;
    private int statusCode;

    private boolean success;

    public ResponseDto(String statusMsg, int statusCode) {
        this.statusMsg = statusMsg;
        this.statusCode = statusCode;
        this.success = true;
    }
}
