package com.hanghae.gamemini.controller;

import com.hanghae.gamemini.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/user")
@RequiredArgsConstructor
public class UserController {

//     private final UserService userService;
     private final PasswordEncoder passwordEncoder;
     private final UserRepository userRepository;
     
     //ResponseEntity 사용 예시
     /*
     @PostMapping ("/signup")
     public ResponseEntity<PrivateResponseBody> signup() {
          PrivateResponseBody privateResponseBody = new PrivateResponseBody();
          return new ResponseEntity<>(new PrivateResponseBody(true, CommonStatusCode.OK, userService.signUp()), HttpStatus.OK);
     }
     */
     
     
     // AuthenticationPrincipal 사용예시
     /*
     @PostMapping("/login")
     public String login(@AuthenticationPrincipal UserDetails userDetails)
      */
}
