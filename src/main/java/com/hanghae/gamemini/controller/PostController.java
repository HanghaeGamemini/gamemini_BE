package com.hanghae.gamemini.controller;


import com.hanghae.gamemini.dto.PostRequestDto;
import com.hanghae.gamemini.dto.PostResponseDto;
import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.security.UserDetailsImpl;
import com.hanghae.gamemini.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    //전체조회
    @GetMapping("/post")
    public ResponseEntity<?> getPost(){
        List<PostResponseDto> postResponseDtos = postService.getPsot();
        ResponseEntity<List<PostResponseDto>>listResponseEntity = new ResponseEntity<>(postResponseDtos, HttpStatus.OK);
        return listResponseEntity;
    }

    //선택조회
    @GetMapping("post/{id}")
    public  ResponseEntity<PrivateResponseBody> detailPost(@PathVariable Long id){
        postService.detailPost(id);
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK),HttpStatus.OK);
    }
    //게시글 작성
    @PostMapping("/post")
    public ResponseEntity<PrivateResponseBody> post(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.createPost(postRequestDto, userDetails.getUser());
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK), HttpStatus.OK);
    }

    //게시글 수정
    @PutMapping("/post/{id}")
    public ResponseEntity<PrivateResponseBody> updatePost(@PathVariable Long id , @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        PrivateResponseBody privateResponseBody = new PrivateResponseBody();
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK,postService.updatePost(id, postRequestDto,userDetails.getUser())),HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity<PrivateResponseBody> deletePost(@PathVariable Long id,  @AuthenticationPrincipal UserDetailsImpl userDetails  ){
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK,postService.deletePost(id, userDetails.getUser())),HttpStatus.OK);

    }
}
