package com.hanghae.gamemini.dto;

import com.hanghae.gamemini.model.TestPost;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TestPostResponseDto {
    private String title;
    private String username;
    private String content;
    private LocalDateTime CreatedAt;
    private LocalDateTime ModifiedAt;
    private int likes;

    private boolean isLike;

//    public TestPostResponseDto(TestPost post) {
//        this.title = post.getTitle();
//        this.username = post.getUser().getUsername();
//        this.content = post.getContent();
//        this.CreatedAt = post.getCreatedAt();
//        this.ModifiedAt = post.getModifiedAt();
//        this.likes = post.getLikes();
//        this.isLike = post.getLike().isLike();
//    }

    public TestPostResponseDto(TestPost post, boolean isLike) {
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.content = post.getContent();
        this.CreatedAt = post.getCreatedAt();
        this.ModifiedAt = post.getModifiedAt();
        this.likes = post.getLikes();
        this.isLike = isLike;
    }




}


