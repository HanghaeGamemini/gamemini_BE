package com.hanghae.gamemini.controller;


import com.amazonaws.util.IOUtils;
import com.hanghae.gamemini.dto.*;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //전체조회
    @GetMapping
    public ResponseEntity<PrivateResponseBody> getPost(
         @RequestParam(value="search", defaultValue = "") String search,
         @RequestParam(value="searchBy", defaultValue = "all") String searchBy,
         @RequestParam(value = "page", defaultValue = "1") int page,
         @RequestParam(value="size", defaultValue = "8") int size
    ){
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, postService.getPost(search, searchBy, page-1, size)), HttpStatus.OK);
    }

    //선택조회
    @GetMapping("/{id}")
    public  ResponseEntity<PrivateResponseBody> detailPost(@PathVariable Long id){
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, postService.detailPost(id)),HttpStatus.OK);
    }

    //게시글 작성
    @PostMapping
    public ResponseEntity<PrivateResponseBody> createPost(
         @ModelAttribute PostRequestDto requestDto){
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.CREATE_POST, postService.createPost(requestDto)), HttpStatus.OK);
    }
    
//    @PostMapping("/test")
//    public ResponseEntity<PrivateResponseBody> createPost3(
//         @RequestPart(value="requestDto", required = true) PostRequestDto requestDto,
//         @RequestPart(value="file", required = false) MultipartFile multipartFile
//         @ModelAttribute PostRequestDto2 requestDto){
//        System.out.println(">>>>>>>>"+ requestDto.getFiles());
//        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.CREATE_POST, postService.createPost2(requestDto)), HttpStatus.OK);
//    }
    
    // 게시글 수정페이지 불러오기
    @GetMapping("/update/{id}")
    public ResponseEntity<PrivateResponseBody> getUpdatePost(
         @PathVariable Long id){
        PostResponseDto.getUpdateResponse responseDto = postService.getUpdatePost(id);
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, responseDto), HttpStatus.OK);
    }

    //게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<PrivateResponseBody> updatePost(
         @PathVariable Long id,
         @ModelAttribute PostRequestDto requestDto){
        log.info(">>>>>>>>>>>> id : {}", id);
        log.info(">>>>>>>>>>>>> content: {}, title: {}", requestDto.getContent(), requestDto.getTitle());
        postService.updatePost(id, requestDto);
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.UPDATE_POST), HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<PrivateResponseBody> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.DELETE_POST), HttpStatus.OK);
    }
}
