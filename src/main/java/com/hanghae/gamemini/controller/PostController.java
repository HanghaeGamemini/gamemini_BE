package com.hanghae.gamemini.controller;


import com.hanghae.gamemini.dto.MsgResponseDto;
import com.hanghae.gamemini.dto.PostListDto;
import com.hanghae.gamemini.dto.PostRequestDto;
import com.hanghae.gamemini.dto.PostResponseDto;
import com.hanghae.gamemini.service.PostService;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpHeaders;
import java.util.List;


@NoArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private PostService postService;

    //전체조회
    @GetMapping("/post")
    public List<PostListDto> readPost(){
    return postService.readPsot();
    }
    //게시글 작성
    @PutMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal  UserDetailsImpl userDetails){
        return postService.createPost(postRequestDto,userDetails.getUser());
    }

    //게시글 수정
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id , @RequestBody PostRequestDto postRequestDto, HttpServletRequest request){
    return postService.updatePost(id, postRequestDto, request);
    }

    //게시글 삭제
    @DeleteMapping("/post/{id}")
    public MsgResponseDto deletePost(@PathVariable Long id, HttpServletRequest request ){
        return postService.deletePost(id, request);

    }
}
