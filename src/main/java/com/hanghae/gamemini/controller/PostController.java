package com.hanghae.gamemini.controller;


import com.hanghae.gamemini.dto.PostRequestDto;
import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.security.UserDetailsImpl;
import com.hanghae.gamemini.service.PostService;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@NoArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private PostService postService;

    //전체조회
    @GetMapping("/post")
    public  ResponseEntity<PrivateResponseBody> getPost(){
        return postService.getPsot();
    }

    //선택조회
    @GetMapping("post/{id}")
    public  ResponseEntity<PrivateResponseBody> detailPost(@PathVariable Long id){
        return postService.detailPost(id);
    }
    //게시글 작성
    @PutMapping("/post")
    public ResponseEntity<PrivateResponseBody> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        PrivateResponseBody privateResponseBody = new PrivateResponseBody();
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, postService.createPost(postRequestDto,userDetails.getUser())), HttpStatus.OK);
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
