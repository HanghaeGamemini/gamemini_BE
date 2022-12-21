package com.hanghae.gamemini.controller;

import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.exception.RestApiException;
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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    @GetMapping("/mypsot/like")
    public ResponseEntity<?> getMyLikePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           @RequestParam(value = "page",defaultValue = "1") int page,
                                           @RequestParam(value = "size", defaultValue = "8") int size) {
        return myPageService.getMyLikePost(userDetails.getUser(),page, size);
    }
    //내가 작성한 댓글 불러오기

    @GetMapping("/mypost/comeent")
    public ResponseEntity<?> getMyCommen(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                         @RequestParam(value = "page",defaultValue = "1") int page,
                                         @RequestParam(value = "size",defaultValue = "8")int size) {
        return myPageService.getMyComment(userDetails.getUser(),page, size);

    }



    //포르필 변경하기
    @PutMapping("/mypost/profile")
    public ResponseEntity<PrivateResponseBody> updateProfile(@RequestPart(value = "file", required = false)MultipartFile multipartFile,
                                                             HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        log.info("realPath : {}", realPath);
        myPageService.updateProfile(multipartFile, realPath);
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK), HttpStatus.OK);

    }



}

