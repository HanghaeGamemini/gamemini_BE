package com.hanghae.gamemini.controller;

import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.dto.UpdateProfileRequestDto;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyPageController {


    private final MyPageService myPageService;

    @PutMapping("/mypage")
    public ResponseEntity<PrivateResponseBody> updateProfile(@RequestBody UpdateProfileRequestDto requestDto){
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, myPageService.updateProfile(requestDto)), HttpStatus.OK);
    }

    @DeleteMapping("/mypage/deleteuser")
    public ResponseEntity<PrivateResponseBody> deleteUser(){
        myPageService.deleteUser();
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.DELETE_USER), HttpStatus.OK);
    }
}
