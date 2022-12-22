package com.hanghae.gamemini.controller;

import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.dto.UpdateProfileRequestDto;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.security.UserDetailsImpl;
import com.hanghae.gamemini.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MyPageController {
    private final MyPageService myPageService;

    //내가 작성한 글 조회
    @Transactional
    @GetMapping("/mypost")
    public ResponseEntity<?> getMyPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @RequestParam(value = "page",defaultValue = "1") int page,
                                       @RequestParam(value = "size",defaultValue = "8")int size) {
        return myPageService.getMyPost(userDetails.getUser(), page, size);
    }

    //내가 좋아요한 게시글 불러오기

    @GetMapping("/mypost/like")
    public ResponseEntity<?> getMyLikePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           @RequestParam(value = "page",defaultValue = "1") int page,
                                           @RequestParam(value = "size", defaultValue = "8") int size) {
        return myPageService.getMyLikePost(userDetails.getUser(),page, size);
    }
    //내가 작성한 댓글 불러오기

    @GetMapping("/mypost/comment")
    public ResponseEntity<?> getMyComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                         @RequestParam(value = "page",defaultValue = "1") int page,
                                         @RequestParam(value = "size",defaultValue = "8")int size) {
        return myPageService.getMyComment(userDetails.getUser(),page, size);

    }

    @PutMapping("")
    public ResponseEntity<PrivateResponseBody> updateProfile(
         @ModelAttribute UpdateProfileRequestDto requestDto){
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, myPageService.updateProfile(requestDto)), HttpStatus.OK);
    }

    @DeleteMapping("/deleteuser")
    public ResponseEntity<PrivateResponseBody> deleteUser(){
        myPageService.deleteUser();
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.DELETE_USER), HttpStatus.OK);
    }
}


