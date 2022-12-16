package com.hanghae.gamemini.handler;

import com.sparta.hanghaestartproject.dto.ErrorResponseDto;
import com.sparta.hanghaestartproject.errorcode.CommonErrorCode;
import com.sparta.hanghaestartproject.errorcode.ErrorCode;
import com.sparta.hanghaestartproject.errorcode.UserErrorCode;
import com.sparta.hanghaestartproject.exception.RestApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
     
     @ExceptionHandler (RestApiException.class)
     public ResponseEntity<Object> handleCustomException(RestApiException e) {
          ErrorCode errorCode = e.getErrorCode();
          return handleExceptionInternal(errorCode);
     }
     
     @ExceptionHandler(IllegalArgumentException.class)
     public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e) {
          log.warn("handleIllegalArgument", e);
          ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
          return handleExceptionInternal(errorCode, e.getMessage());
     }
     
     @Override
     protected ResponseEntity<Object> handleMethodArgumentNotValid(
                    MethodArgumentNotValidException e,
                    HttpHeaders headers,
                    HttpStatus status,
                    WebRequest request) {
          log.warn("handleMethodArgumentNotValid", e);
          String errorFieldName = e.getBindingResult().getFieldError().getField();
          ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
          if(errorFieldName.equals("username")){
               errorCode = UserErrorCode.WRONG_USERNAME_PATTERN;
          }else if(errorFieldName.equals("password")){
               errorCode = UserErrorCode.WRONG_PASSWORD_PATTERN;
          }
          return handleExceptionInternal(errorCode);
     }
     
     @ExceptionHandler(ConstraintViolationException.class)
     public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e) {
          log.warn("handleConstraintViolation", e);
          ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
          String interpolatedMessage = e.getMessage().split("interpolatedMessage=\'")[1].split("\', propertyPath")[0];
          System.out.println(e.getMessage());
          return handleExceptionInternal(errorCode, interpolatedMessage);
     }
     
     @ExceptionHandler({Exception.class})
     public ResponseEntity<Object> handleAllException(Exception ex) {
          log.warn("handleAllException", ex);
          ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
          return handleExceptionInternal(errorCode);
     }
     
     private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
          return ResponseEntity.status(errorCode.getStatusCode())
               .body(makeErrorResponse(errorCode));
     }
     
     private ErrorResponseDto makeErrorResponse(ErrorCode errorCode) {
          return ErrorResponseDto.builder()
               .statusCode(errorCode.getStatusCode())
               .msg(errorCode.getMsg())
               .build();
     }
     
     private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, String message) {
          return ResponseEntity.status(errorCode.getStatusCode())
               .body(makeErrorResponse(errorCode, message));
     }
     
     private ErrorResponseDto makeErrorResponse(ErrorCode errorCode, String message) {
          return ErrorResponseDto.builder()
               .statusCode(errorCode.getStatusCode())
               .msg(message)
               .build();
     }
}
