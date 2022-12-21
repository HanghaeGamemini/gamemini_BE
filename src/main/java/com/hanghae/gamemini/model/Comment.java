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

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;
    @Column
    private String nickname;

    @Column(nullable = false)
    private boolean deleted;

    public Comment(String nickname, Post post, CommentRequestDto requestDto) {
        this.nickname = nickname;
        this.post = post;
        this.content = requestDto.getContent();
    }


    public void deletedUpdate() {
        this.deleted = true;
    }
}
