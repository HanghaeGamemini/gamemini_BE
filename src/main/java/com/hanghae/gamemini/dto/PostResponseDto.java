package com.hanghae.gamemini.dto;

import com.hanghae.gamemini.model.Post;

public class PostResponseDto {
    private String title;
    private String content;
    public PostResponseDto(Post post) {
        this.title = post.getTitle();

    }
}
