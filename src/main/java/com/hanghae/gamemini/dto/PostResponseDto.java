package com.hanghae.gamemini.dto;

import com.hanghae.gamemini.model.Post;

import java.time.LocalDateTime;

public class PostResponseDto {
    private String title;

    private String username;

    private String nickName;

    private String content;
    private LocalDateTime CreatedAt;

    private LocalDateTime ModifedAt;

    private String imgUrl;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.CreatedAt = post.getCreatedAt();
        this.ModifedAt = post.getModifiedAt();
        this.imgUrl = post.getImgUrl();
    }
}
