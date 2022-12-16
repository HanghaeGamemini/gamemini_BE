package com.hanghae.gamemini.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

    @Size(min = 5, max = 10, message="아이디 길이를 4자 이상 10자 이하로 해주세요")
    @Pattern(regexp = "^(?=.*?[0-9])(?=.*?[a-z])", message = "아이디를 알파벳소문자 또는 숫자로만 구성해주세요")
    private String username;

    @Size(min = 8, max = 15, message = "비밀번호 길이를 8자 이상 15자 이하로 해주세요")
    @Pattern (regexp="^.(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "비밀번호에 알파벳대소문자, 숫자, 특수문자가 포함되게 해주세요")
    private String password;


    private String nickname;

}
