package com.hanghae.gamemini.controller;

import com.hanghae.gamemini.dto.LoginRequestDto;
import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.dto.SignupRequestDto;
import com.hanghae.gamemini.errorcode.UserStatusCode;
import com.hanghae.gamemini.repository.UserRepository;
import com.hanghae.gamemini.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping ("/api/user")
@RequiredArgsConstructor
public class UserController {

     private final PasswordEncoder passwordEncoder;
     private final UserRepository userRepository;
     private final UserService userService;
     
     //ResponseEntity 사용 예시
     
     @PostMapping ("/signup")
     public ResponseEntity<PrivateResponseBody> signup(@RequestBody @Valid SignupRequestDto requestDto) {
          userService.signUp(requestDto);
          return new ResponseEntity<>(new PrivateResponseBody(UserStatusCode.USER_SIGNUP_SUCCESS), HttpStatus.OK);
     }

     @PostMapping ("/login")
     public ResponseEntity<PrivateResponseBody> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
          return new ResponseEntity<>(new PrivateResponseBody(UserStatusCode.USER_LOGIN_SUCCESS, userService.login(loginRequestDto, response)), HttpStatus.OK);
     }
}
