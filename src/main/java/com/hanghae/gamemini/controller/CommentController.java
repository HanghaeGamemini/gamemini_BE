package com.hanghae.gamemini.controller;

import com.hanghae.gamemini.dto.CommentRequestDto;
import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<PrivateResponseBody> postComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto) {
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, commentService.postComment(postId, requestDto)), HttpStatus.OK);
    }

    @DeleteMapping("/post/comment/{commentId}")
    public ResponseEntity<PrivateResponseBody> deleteComment(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }


}
