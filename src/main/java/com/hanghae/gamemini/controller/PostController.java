package com.hanghae.gamemini.controller;


import com.hanghae.gamemini.dto.PostRequestDto;
import com.hanghae.gamemini.dto.PostResponseDto;
import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.security.UserDetailsImpl;
import com.hanghae.gamemini.service.PostService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@NoArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private PostService postService;

    //전체조회
    @GetMapping("/post")
    public ResponseEntity<PrivateResponseBody> getPost(
         @RequestParam(value = "page", defaultValue = "1") int page,
         @RequestParam(value="size", defaultValue = "5") int size
    ){
        List<PostResponseDto.AllPostResponseDto> postResponseDtos = postService.getPost(page-1, size);
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, postResponseDtos), HttpStatus.OK);
    }

    //선택조회
    @GetMapping("post/{id}")
    public  ResponseEntity<PrivateResponseBody> detailPost(@PathVariable Long id){
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, postService.detailPost(id)),HttpStatus.OK);
    }
    //게시글 작성
    @PostMapping("/post")
    public ResponseEntity<PrivateResponseBody> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.createPost(postRequestDto, userDetails.getUser());
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK), HttpStatus.OK);
    }
    
    @PostMapping("/post2")
    public ResponseEntity<PrivateResponseBody> createPost2(
         @RequestPart PostRequestDto postRequestDto,
         @RequestPart(value="file", required = false) MultipartFile multipartFile, HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        log.info("realPath : {}", realPath);
        postService.createPost2(postRequestDto , multipartFile, realPath);
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
