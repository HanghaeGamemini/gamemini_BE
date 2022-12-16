package com.hanghae.gamemini.dto;

public class MsgResponseDto {
    private String statusMsg;
    private int statusCode;
    private String HttpStatus;

    public MsgResponseDto(String statusMsg, int statusCode){
        this.statusMsg = statusMsg;
        this.statusCode = statusCode;

    }
}
