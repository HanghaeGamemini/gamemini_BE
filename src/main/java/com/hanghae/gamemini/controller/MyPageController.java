package com.hanghae.gamemini.controller;

import com.hanghae.gamemini.security.UserDetailsImpl;
import com.hanghae.gamemini.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MyPageController {

    private final MyPageService myPageService;

    //내가 작성한 글 불러오기
    @GetMapping("/mypost")
    public ResponseEntity<?> getMyPost(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return myPageService.getMyPost(userDetails.getUser());
    }
    //내가 작성한 댓글 불러오기

    @GetMapping("/mypost/comeent")
    public ResponseEntity<?> getMyComment(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return myPageService.getMyComment(userDetails.getUser());
    }

    //내가 좋아요한 게시글 불러오기

    @GetMapping("/mypsot/like")
    public ResponseEntity<?> getMyLikePost(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return myPageService.getMyLikePost(userDetails.getUser());
    }

    //포르필 변경하기
//    @PutMapping("/mypost/profile")
//    public ResponseEntity<?> changeProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        return myPageService.chageProfile(userDetails.getUser());
//    }
}
