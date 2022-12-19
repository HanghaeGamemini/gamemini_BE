package com.hanghae.gamemini.controller;


import com.hanghae.gamemini.dto.PostRequestDto;
import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.security.UserDetailsImpl;
import com.hanghae.gamemini.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //전체조회
    @GetMapping("/post")
    public ResponseEntity<PrivateResponseBody> getPost(
         @RequestParam(value = "page", defaultValue = "1") int page,
         @RequestParam(value="size", defaultValue = "5") int size
    ){
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, postService.getPost(page-1, size)), HttpStatus.OK);
    }

    //선택조회
    @GetMapping("post/{id}")
    public  ResponseEntity<PrivateResponseBody> detailPost(@PathVariable Long id){
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, postService.detailPost(id)),HttpStatus.OK);
    }

    //게시글 작성
    @PostMapping("/post")
    public ResponseEntity<PrivateResponseBody> createPost(
         @RequestPart PostRequestDto requestDto,
         @RequestPart(value="file", required = false) MultipartFile multipartFile,
         HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        postService.createPost(requestDto, multipartFile);
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.CREATE_POST), HttpStatus.OK);
    }
    
    // 서버에 이미지 저장ver
    @PostMapping("/post2")
    public ResponseEntity<PrivateResponseBody> createPost2(
         @RequestPart PostRequestDto postRequestDto,
         @RequestPart(value="file", required = false) MultipartFile multipartFile,
         HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        log.info("realPath : {}", realPath);
        postService.createPost2(postRequestDto , multipartFile, realPath);
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK), HttpStatus.OK);
    }

    //게시글 수정
    @PutMapping("/post/{id}")
    public ResponseEntity<PrivateResponseBody> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PrivateResponseBody privateResponseBody = new PrivateResponseBody();
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, postService.updatePost(id, postRequestDto, userDetails.getUser())), HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity<PrivateResponseBody> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, postService.deletePost(id, userDetails.getUser())), HttpStatus.OK);

    }
}
