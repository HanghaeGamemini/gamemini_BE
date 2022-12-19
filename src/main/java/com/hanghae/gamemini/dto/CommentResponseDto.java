package com.hanghae.gamemini.dto;

import com.hanghae.gamemini.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String nickname;
    private String content;
    private LocalDateTime CreatedAt;
    private LocalDateTime ModifiedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
//        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        CreatedAt = comment.getCreatedAt();
        ModifiedAt = comment.getModifiedAt();
    }


}
