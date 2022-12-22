package com.hanghae.gamemini.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum UserStatusCode implements StatusCode {

     NOT_FOUND_AUTHORIZATION_IN_SECURITY_CONTEXT("Security Context에 인증 정보가 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
     USER_SIGNUP_SUCCESS(true, "회원가입 성공", HttpStatus.OK.value()),

     USER_LOGIN_SUCCESS(true, "로그인 성공", HttpStatus.OK.value()),

     ONLY_FOR_ADMIN("관리자만 가능합니다.", HttpStatus.BAD_REQUEST.value()),
     WRONG_USERNAME_PATTERN("유저명은 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 합니다.", HttpStatus.BAD_REQUEST.value()),
     WRONG_PASSWORD_PATTERN("비밀번호는 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자로 구성되어야 합니다.", HttpStatus.BAD_REQUEST.value()),
     WRONG_PASSWORD("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST.value()),
     NO_USER("회원을 찾을 수 없습니다.",HttpStatus.BAD_REQUEST.value()),
     DELETE_USER("탈퇴한 사용자 입니다.",HttpStatus.BAD_REQUEST.value()),

     WRONG_ADMIN_TOKEN("관리자 암호가 틀려 등록이 불가능합니다.", HttpStatus.BAD_REQUEST.value()),
     OVERLAPPED_USERNAME("중복된 username 입니다.", HttpStatus.BAD_REQUEST.value()),
     OVERLAPPED_NICKNAME("중복된 닉네임 입니다.", HttpStatus.BAD_REQUEST.value()),
     PASSWORD_CHECK("입력된 비밀번호가 다릅니다.", HttpStatus.BAD_REQUEST.value()),
     임시이넘객체("임시객체입니다", HttpStatus.BAD_REQUEST.value()),
     INVALID_TOKEN("토큰이 유효하지 않습니다.", HttpStatus.BAD_REQUEST.value());

     private boolean success = false;
     private final String statusMsg;
     private final int statusCode;
}