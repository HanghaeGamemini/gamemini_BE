package com.hanghae.gamemini.dto;

import com.hanghae.gamemini.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto {
    private String title;
    private String username;
    private String content;

    private String nickName;

    private List<CommentResponseDto> comments;
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

    public PostResponseDto(Post post, boolean isLike) {
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.content = post.getContent();
        this.comments = post.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        this.CreatedAt = post.getCreatedAt();
        this.ModifiedAt = post.getModifiedAt();
        this.likes = post.getLikes();
        this.isLike = isLike;
    }


    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.content = post.getContent();
        this.comments = post.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        this.CreatedAt = post.getCreatedAt();
        this.ModifiedAt = post.getModifiedAt();
        this.likes = post.getLikes();
    }
}


