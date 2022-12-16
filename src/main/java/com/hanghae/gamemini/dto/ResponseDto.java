package com.hanghae.gamemini.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDto {

    private String statusMsg;

    private String statusCode;

    private boolean success;

    public ResponseDto(String statusCode, String statusMsg){

    }
}
