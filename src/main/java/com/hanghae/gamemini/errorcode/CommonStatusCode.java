package com.hanghae.gamemini.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum CommonStatusCode implements StatusCode {
     OK(true,"정상", HttpStatus.OK.value()),
     INVALID_PARAMETER("Invalid parameter included",HttpStatus.BAD_REQUEST.value()),
     INTERNAL_SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value()),
     NO_ARTICLE("게시글이 존재하지 않습니다", HttpStatus.NOT_FOUND.value()),
     NO_COMMENT("댓글이 존재하지 않습니다.", HttpStatus.NOT_FOUND.value()),
     INVALID_USER("작성자만 삭제/수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value());
     
     private boolean success = false;
     private final String StatusMsg;
     private final int statusCode;
     
}
