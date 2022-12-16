package com.hanghae.gamemini.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PrivateResponseBody<T> {

    private boolean success;
    private int statusCode;
    private String statusMsg;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data; //null일 경우 json에 안보내지도록

    public PrivateResponseBody(StatusCode statusCode){
        this.statusCode = statusCode.

    }


}
