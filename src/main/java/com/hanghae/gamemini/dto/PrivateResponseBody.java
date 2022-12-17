package com.hanghae.gamemini.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hanghae.gamemini.errorcode.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PrivateResponseBody<T> {
     private boolean success;
     private int statusCode;
     private String statusMsg;
     
     @JsonInclude (JsonInclude.Include.NON_EMPTY)
     private T data; // null 일경우 json에 안보내지도록
     
     public PrivateResponseBody(StatusCode statusCode) {
          this.success = statusCode.isSuccess();
          this.statusCode = statusCode.getStatusCode();
          this.statusMsg = statusCode.getStatusMsg();
          this.success = true;
     }
     
     public PrivateResponseBody(StatusCode statusCode, T data){
          this.success = statusCode.isSuccess();
          this.statusCode = statusCode.getStatusCode();
          this.statusMsg = statusCode.getStatusMsg();
          this.data = data;
     }


}
