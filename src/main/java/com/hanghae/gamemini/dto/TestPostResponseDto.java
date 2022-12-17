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

    public TestPostResponseDto(TestPost post) {
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.content = post.getContent();
        this.CreatedAt = post.getCreatedAt();
        this.ModifiedAt = post.getModifiedAt();
    }
}
