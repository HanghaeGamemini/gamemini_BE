package com.hanghae.gamemini.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum CommonStatusCode implements StatusCode {
     FILE_SAVE_FAIL("파일 저장에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
     OK(true,"정상", HttpStatus.OK.value()),

     WRONG_IMAGE_FORMAT("지원하지 않는 파일 형식입니다.", HttpStatus.BAD_REQUEST.value()),

     POST_LIKE(true, "좋아요", HttpStatus.OK.value()),
     POST_LIKE_CANCEL(true, "좋아요취소", HttpStatus.OK.value()),
     DELETE_COMMENT(true, "댓글 삭제 성공", HttpStatus.OK.value()),


     INVALID_PARAMETER("Invalid parameter included",HttpStatus.BAD_REQUEST.value()),
     INTERNAL_SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value()),
     NO_ARTICLE("게시글이 존재하지 않습니다", HttpStatus.NOT_FOUND.value()),
     NO_COMMENT("댓글이 존재하지 않습니다.", HttpStatus.NOT_FOUND.value()),
     INVALID_USER("작성자만 삭제/수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value());
     
     private boolean success = false;
     private final String StatusMsg;
     private final int statusCode;
     
}
