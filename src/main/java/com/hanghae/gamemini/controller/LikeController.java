package com.hanghae.gamemini.controller;

import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/likes/{postId}")
    public ResponseEntity<PrivateResponseBody> PostLike(@PathVariable Long postId){
        return likeService.PostLike(postId);
    }


}
