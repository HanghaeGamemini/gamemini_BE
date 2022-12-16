package com.hanghae.gamemini.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDto {
<<<<<<< HEAD

    private String statusMsg;

    private String statusCode;

    private boolean success;

    public ResponseDto(String statusCode, String statusMsg){

=======
    private String statusMsg;
    private int statusCode;
    private boolean success;

    public ResponseDto(String statusMsg, int statusCode) {
        this.statusMsg = statusMsg;
        this.statusCode = statusCode;
        this.success = true;
>>>>>>> f6ed22110bc03a21a8ffd17646954982b6905e55
    }
}
