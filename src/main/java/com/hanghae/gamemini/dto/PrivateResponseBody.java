package com.hanghae.gamemini.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hanghae.gamemini.errorcode.StatusCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PrivateResponseBody<T> {
     private boolean success;
     private int statusCode;
     private String statusMsg;
     
     @ApiModelProperty(value="실제 데이터")
     @JsonInclude (JsonInclude.Include.NON_EMPTY)
     private Object data; // null 일경우 json에 안보내지도록
     
     public PrivateResponseBody(StatusCode statusCode) {
          this.success = statusCode.isSuccess();
          this.statusCode = statusCode.getStatusCode();
          this.statusMsg = statusCode.getStatusMsg();
     }
     
     public PrivateResponseBody(StatusCode statusCode, Object data){
          this.success = statusCode.isSuccess();
          this.statusCode = statusCode.getStatusCode();
          this.statusMsg = statusCode.getStatusMsg();
          this.data = data;
     }


}
