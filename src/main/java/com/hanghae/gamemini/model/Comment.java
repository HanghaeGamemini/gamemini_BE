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
    private String username;
    @Transient
    private String nickname;

    public Comment(User user, Post post, CommentRequestDto requestDto) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.post = post;
        this.content = requestDto.getContent();
    }
    
    public Comment(CommentNicknameInterface commentNicknameInterface){
        this.id = commentNicknameInterface.getCommentId();
        this.content = commentNicknameInterface.getContent();
        this.username = commentNicknameInterface.getUsername();
        this.nickname = commentNicknameInterface.getNickname();
        this.setCreatedAt(commentNicknameInterface.getCreated_at());
    }


}
