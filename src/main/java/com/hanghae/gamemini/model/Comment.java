package com.hanghae.gamemini.model;

import com.hanghae.gamemini.dto.CommentRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity(name = "comment")
@RequiredArgsConstructor
@Getter
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String content;

    @ManyToOne
    @JoinColumn
    private TestPost post;

    @ManyToOne
    @JoinColumn
    private User user;


    public Comment(User user, TestPost post, CommentRequestDto requestDto) {
        this.user = user;
        this.post = post;
        this.content = requestDto.getContent();
    }


}
