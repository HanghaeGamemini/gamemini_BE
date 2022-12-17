package com.hanghae.gamemini.controller;

import com.hanghae.gamemini.dto.TestPostRequestDto;
import com.hanghae.gamemini.dto.TestPostResponseDto;
import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.security.UserDetailsImpl;
import com.hanghae.gamemini.service.TestPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestPostContorller {

    private final TestPostService postService;

    @PostMapping("/testpost")
    public ResponseEntity<PrivateResponseBody> post(@RequestBody TestPostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.post(requestDto, userDetails.getUser());
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK), HttpStatus.OK);
    }

    @GetMapping("/testpost")
    public List<TestPostResponseDto> readPost(){
        return postService.readPost();
    }

}
