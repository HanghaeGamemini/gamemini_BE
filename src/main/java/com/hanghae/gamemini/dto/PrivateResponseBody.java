package com.hanghae.gamemini.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
<<<<<<< HEAD
=======
import com.hanghae.gamemini.errorcode.StatusCode;
import lombok.Builder;
>>>>>>> f6ed22110bc03a21a8ffd17646954982b6905e55
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PrivateResponseBody<T> {
<<<<<<< HEAD

    private boolean success;
    private int statusCode;
    private String statusMsg;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data; //null일 경우 json에 안보내지도록

    public PrivateResponseBody(StatusCode statusCode){
        this.statusCode = statusCode.

    }


=======
     private boolean success;
     private int statusCode;
     private String statusMsg;
     
     @JsonInclude (JsonInclude.Include.NON_EMPTY)
     private T data; // null 일경우 json에 안보내지도록
     
     public PrivateResponseBody(StatusCode statusCode) {
          this.statusCode = statusCode.getStatusCode();
          this.statusMsg = statusCode.getStatusMsg();
     }
     public PrivateResponseBody(StatusCode statusCode, T data){
          this.statusCode = statusCode.getStatusCode();
          this.statusMsg = statusCode.getStatusMsg();
          this.data = data;
     }
     
     public PrivateResponseBody(boolean success, StatusCode statusCode, T data){
          this.success = statusCode.isSuccess();
          this.statusCode = statusCode.getStatusCode();
          this.statusMsg = statusCode.getStatusMsg();
          this.data = data;
     }
>>>>>>> f6ed22110bc03a21a8ffd17646954982b6905e55
}
