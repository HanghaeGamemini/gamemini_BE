package com.hanghae.gamemini.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

    @Size(min = 5, max = 10)
    @Pattern(regexp = "^(?=.*?[0-9])(?=.*?[a-z])")
    private String username;

    //@Pattern (regexp="^(?=.*?[0-9])(?=.*?[a-z])(?=.*?[#?!@$%.^&*-])(?=\\S+$).{8,15}$")
    @Size(min = 8, max = 15)
    @Pattern (regexp="^.(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$")
    private String password;


    private String nickname;

}
