package com.hanghae.gamemini.controller;


import com.hanghae.gamemini.dto.PostRequestDto;
import com.hanghae.gamemini.dto.PostResponseDto;
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
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //전체조회
    @GetMapping
    public ResponseEntity<PrivateResponseBody> getPost(
         @RequestParam(value = "page", defaultValue = "1") int page,
         @RequestParam(value="size", defaultValue = "5") int size
    ){
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, postService.getPost(page-1, size)), HttpStatus.OK);
    }

    //선택조회
    @GetMapping("/{id}")
    public  ResponseEntity<PrivateResponseBody> detailPost(@PathVariable Long id){
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, postService.detailPost(id)),HttpStatus.OK);
    }

    //게시글 작성
    @PostMapping
    public ResponseEntity<PrivateResponseBody> createPost(
         @RequestPart(value="requestDto", required = true) PostRequestDto requestDto,
         @RequestPart(value="file", required = false) MultipartFile multipartFile,
         HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.CREATE_POST, postService.createPost(requestDto, multipartFile)), HttpStatus.OK);
    }
    
    // 서버에 이미지 저장ver
    @PostMapping("/post2")
    public ResponseEntity<PrivateResponseBody> createPost2(
         @RequestPart PostRequestDto postRequestDto,
         @RequestPart(value="file", required = false) MultipartFile multipartFile,
         HttpServletRequest request){  // 필요없을지도
        String realPath = request.getSession().getServletContext().getRealPath("/");
        log.info("realPath : {}", realPath);
        postService.createPost2(postRequestDto , multipartFile, realPath);  // 필요없을지도
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK), HttpStatus.OK);
    }

    //게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<PrivateResponseBody> updatePost(
         @PathVariable Long id,
         @RequestPart(value="requestDto", required = true) PostRequestDto postRequestDto,
         @RequestPart(value="file", required = false) MultipartFile multipartFile){
        PostResponseDto.DetailResponse responseDto = postService.updatePost(id, postRequestDto, multipartFile);
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, responseDto), HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<PrivateResponseBody> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.DELETE_POST), HttpStatus.OK);
    }
}
